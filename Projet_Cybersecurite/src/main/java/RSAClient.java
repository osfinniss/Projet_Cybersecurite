import java.io.*;
import java.math.BigInteger;
import java.net.Socket;

public class RSAClient {
    private static final String SERVER_ADDRESS = "172.20.10.2";
    private static final int SERVER_PORT = 5000;

    public void startClient(String message) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            // Recevoir la clé publique du serveur
            BigInteger n = new BigInteger(in.readUTF());
            BigInteger e = new BigInteger(in.readUTF());

            // Chiffrer le message
            BigInteger numericMessage = new BigInteger(message.getBytes());
            BigInteger encryptedMessage = numericMessage.modPow(e, n);

            // Envoyer le message chiffré au serveur
            out.writeUTF(encryptedMessage.toString());
            System.out.println("Message chiffré envoyé au serveur.");

            // Recevoir le message déchiffré du serveur
            String decryptedMessage = in.readUTF();
            System.out.println("Message déchiffré reçu du serveur : " + decryptedMessage);

            // Comparer le message original avec le message déchiffré
            if (message.equals(decryptedMessage)) {
                System.out.println("Le message déchiffré correspond au message original.");
            } else {
                System.out.println("Le message déchiffré ne correspond pas au message original.");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RSAClient client = new RSAClient();
        client.startClient("Projet Securite Rsa");
    }
}