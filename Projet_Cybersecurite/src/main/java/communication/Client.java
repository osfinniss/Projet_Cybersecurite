package communication;

import entities.RSAUser;
import utils.RSAUtils;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        RSAUser alice = new RSAUser("Alice", 1024);

        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            String message = "Hello Bob, ceci est un message sécurisé!";
            String encryptedMessage = RSAUtils.encrypt(message, alice.getKeyPair().getPublicKey(), alice.getKeyPair().getModulus());

            output.println(encryptedMessage);
            System.out.println("Message chiffré envoyé: " + encryptedMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
