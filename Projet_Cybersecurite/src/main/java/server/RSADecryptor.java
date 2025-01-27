package server;

import java.math.BigInteger;

public class RSADecryptor {
    private static final BigInteger d = new BigInteger("4279");
    private static final BigInteger n = new BigInteger("5141");

    public static String decrypt(String encryptedMessage) {
        BigInteger c = new BigInteger(encryptedMessage);
        BigInteger m = c.modPow(d, n);
        return new String(m.toByteArray());
    }
}
