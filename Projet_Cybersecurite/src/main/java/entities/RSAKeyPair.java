package entities;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAKeyPair {
    private BigInteger publicKey;
    private BigInteger privateKey;
    private BigInteger modulus;

    public RSAKeyPair(int bitLength) {
        SecureRandom random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);
        modulus = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        publicKey = new BigInteger("65537");  // Exposant public standard
        privateKey = publicKey.modInverse(phi);
    }

    public BigInteger getPublicKey() { return publicKey; }
    public BigInteger getPrivateKey() { return privateKey; }
    public BigInteger getModulus() { return modulus; }
}
