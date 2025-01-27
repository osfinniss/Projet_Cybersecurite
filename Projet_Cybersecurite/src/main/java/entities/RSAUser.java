package entities;

public class RSAUser {
    private String name;
    private RSAKeyPair keyPair;

    public RSAUser(String name, int keySize) {
        this.name = name;
        this.keyPair = new RSAKeyPair(keySize);
    }

    public String getName() { return name; }
    public RSAKeyPair getKeyPair() { return keyPair; }
}
