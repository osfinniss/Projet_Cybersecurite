package server;

import java.math.BigInteger;

public class RSAEncryptor {
    public static String encrypt(String message, BigInteger e, BigInteger n) {
        BigInteger m = new BigInteger(message.getBytes());
        return m.modPow(e, n).toString();
    }
}
