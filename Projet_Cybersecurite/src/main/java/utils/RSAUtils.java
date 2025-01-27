package utils;

import java.math.BigInteger;

public class RSAUtils {
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }

    public static BigInteger modInverse(BigInteger a, BigInteger m) {
        return a.modInverse(m);
    }
}
