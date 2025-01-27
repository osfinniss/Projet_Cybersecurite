package server;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAKeyGenerator {
    private BigInteger n, e, d;

    public RSAKeyGenerator(int bitLength) {
        SecureRandom random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);
        n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        e = new BigInteger("65537");
        d = e.modInverse(phi);
    }

    public BigInteger getPublicKey() { return e; }
    public BigInteger getPrivateKey() { return d; }
    public BigInteger getModulus() { return n; }
}
