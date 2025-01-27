package client;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            String message = "Bonjour!";
            String encryptedMessage = MessageSender.encryptMessage(message);
            output.println(encryptedMessage);
            System.out.println("Message envoyé: " + encryptedMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
