package client;

import server.RSAEncryptor;

import java.math.BigInteger;

public class MessageSender {
    private static final BigInteger e = new BigInteger("7");
    private static final BigInteger n = new BigInteger("5141");

    public static String encryptMessage(String message) {
        return RSAEncryptor.encrypt(message, e, n);
    }
}
