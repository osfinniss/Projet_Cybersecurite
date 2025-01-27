package communication;

import entities.RSAUser;
import utils.RSAUtils;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        // Création de l'utilisateur Bob avec génération de ses clés RSA
        RSAUser bob = new RSAUser("Bob", 1024);

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Bob attend des messages...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connecté.");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String encryptedMessage = input.readLine();

            System.out.println("Message chiffré reçu: " + encryptedMessage);

            // Déchiffrement du message avec la clé privée de Bob
            String decryptedMessage = RSAUtils.decrypt(encryptedMessage, bob.getKeyPair().getPrivateKey(), bob.getKeyPair().getModulus());

            System.out.println("Message déchiffré: " + decryptedMessage);

            // Vérification de l'intégrité du message
            System.out.println("Le message final est identique : " + decryptedMessage.equals("Hello Bob, ceci est un message sécurisé!"));

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
