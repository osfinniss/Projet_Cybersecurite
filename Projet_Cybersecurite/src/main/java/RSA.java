import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

    private BigInteger p, q, n, m, e, u;
    private SecureRandom random;

    // Constructor to generate keys
    public RSA(int bitLength) {
        random = new SecureRandom();

        // Generate two large prime numbers
        p = BigInteger.probablePrime(bitLength, random);
        q = BigInteger.probablePrime(bitLength, random);

        // Calculate n = p * q and m = (p - 1) * (q - 1)
        n = p.multiply(q);
        m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Find e (public exponent) such that 1 < e < m and gcd(e, m) = 1
        do {
            e = new BigInteger(bitLength / 2, random);
        } while (!e.gcd(m).equals(BigInteger.ONE) || e.compareTo(m) >= 0);

        // Calculate u (private key) such that (e * u) mod m = 1
        u = e.modInverse(m);
    }

    // Encrypt a message
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    // Decrypt a message
    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(u, n);
    }

    // Getters for keys
    public BigInteger getPublicKeyN() {
        return n;
    }

    public BigInteger getPublicKeyE() {
        return e;
    }

    public BigInteger getPrivateKeyU() {
        return u;
    }

    public static void main(String[] args) {
        // Define bit length for the keys
        int bitLength = 512;

        // Create RSA object and generate keys
        RSA rsa = new RSA(bitLength);

        System.out.println("Public Key (n, e):");
        System.out.println("n = " + rsa.getPublicKeyN());
        System.out.println("e = " + rsa.getPublicKeyE());

        System.out.println("\nPrivate Key (u):");
        System.out.println("u = " + rsa.getPrivateKeyU());

        // Example message
        String message = "Hello, RSA!";
        BigInteger numericMessage = new BigInteger(message.getBytes());

        // Encrypt the message
        BigInteger encryptedMessage = rsa.encrypt(numericMessage);
        System.out.println("\nEncrypted Message:");
        System.out.println(encryptedMessage);

        // Decrypt the message
        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
        String originalMessage = new String(decryptedMessage.toByteArray());

        System.out.println("\nDecrypted Message:");
        System.out.println(originalMessage);
    }
}