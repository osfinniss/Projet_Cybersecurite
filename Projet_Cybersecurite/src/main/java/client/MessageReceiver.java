package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageReceiver {
    public static void receiveMessage(Socket socket) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Réponse du serveur: " + input.readLine());
    }
}
