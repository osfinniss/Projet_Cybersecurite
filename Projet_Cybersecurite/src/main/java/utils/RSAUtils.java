package utils;

import java.math.BigInteger;

public class RSAUtils {
    public static String encrypt(String message, BigInteger e, BigInteger n) {
        BigInteger m = new BigInteger(message.getBytes());
        return m.modPow(e, n).toString();
    }

    public static String decrypt(String encryptedMessage, BigInteger d, BigInteger n) {
        BigInteger c = new BigInteger(encryptedMessage);
        BigInteger m = c.modPow(d, n);
        return new String(m.toByteArray());
    }
}
