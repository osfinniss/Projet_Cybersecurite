package server;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Serveur en attente de connexion...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connecté.");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String encryptedMessage = input.readLine();
            System.out.println("Message chiffré reçu: " + encryptedMessage);

            String decryptedMessage = RSADecryptor.decrypt(encryptedMessage);
            System.out.println("Message déchiffré: " + decryptedMessage);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
