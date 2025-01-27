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

            // Receive public key from server
            BigInteger n = new BigInteger(in.readUTF());
            BigInteger e = new BigInteger(in.readUTF());

            // Encrypt the message
            BigInteger numericMessage = new BigInteger(message.getBytes());
            BigInteger encryptedMessage = numericMessage.modPow(e, n);

            // Send encrypted message to server
            out.writeUTF(encryptedMessage.toString());
            System.out.println("Encrypted message sent to server.");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RSAClient client = new RSAClient();
        client.startClient("Hello Server!");
    }
}