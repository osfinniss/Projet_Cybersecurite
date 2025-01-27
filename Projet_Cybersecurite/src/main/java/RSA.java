import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

    private BigInteger p, q, n, m, e, u;
    private SecureRandom random;

    // Constructeur pour générer les clés
    public RSA(int bitLength) {
        random = new SecureRandom();

        // Générer deux grands nombres premiers
        p = BigInteger.probablePrime(bitLength, random);
        q = BigInteger.probablePrime(bitLength, random);

        // Calculer n = p * q et m = (p - 1) * (q - 1)
        n = p.multiply(q);
        m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Trouver e (exposant public) tel que 1 < e < m et gcd(e, m) = 1
        do {
            e = new BigInteger(bitLength / 2, random);
        } while (!e.gcd(m).equals(BigInteger.ONE) || e.compareTo(m) >= 0);

        // Calculer u (clé privée) tel que (e * u) mod m = 1
        u = e.modInverse(m);
    }

    // Chiffrer un message
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    // Déchiffrer un message
    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(u, n);
    }

    // Accesseurs pour les clés
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
        // Définir la longueur en bits pour les clés
        int bitLength = 512;

        // Créer un objet RSA et générer les clés
        RSA rsa = new RSA(bitLength);

        System.out.println("Clé publique (n, e) :");
        System.out.println("n = " + rsa.getPublicKeyN());
        System.out.println("e = " + rsa.getPublicKeyE());

        System.out.println("\nClé privée (u) :");
        System.out.println("u = " + rsa.getPrivateKeyU());

        // Exemple de message
        String message = "Hello, RSA!";
        BigInteger numericMessage = new BigInteger(message.getBytes());

        // Chiffrer le message
        BigInteger encryptedMessage = rsa.encrypt(numericMessage);
        System.out.println("\nMessage chiffré :");
        System.out.println(encryptedMessage);

        // Déchiffrer le message
        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
        String originalMessage = new String(decryptedMessage.toByteArray());

        System.out.println("\nMessage déchiffré :");
        System.out.println(originalMessage);
    }
}