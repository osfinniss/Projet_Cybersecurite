import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;

public class RSAServer {
    private static final int PORT = 5000;
    private BigInteger n, e, d;

    public RSAServer(int bitLength) {
        SecureRandom random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitLength, random);
        BigInteger q = BigInteger.probablePrime(bitLength, random);
        n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        do {
            e = new BigInteger(bitLength / 2, random);
        } while (!e.gcd(phi).equals(BigInteger.ONE));

        d = e.modInverse(phi);
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Le serveur RSA fonctionne sur le port " + PORT);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connecté.");

            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());

            // Envoyer la clé publique au client
            out.writeUTF(n.toString());
            out.writeUTF(e.toString());

            // Recevoir le message chiffré
            String encryptedMessage = in.readUTF();
            BigInteger encryptedBigInt = new BigInteger(encryptedMessage);

            // Déchiffrer le message
            BigInteger decryptedMessage = encryptedBigInt.modPow(d, n);
            String message = new String(decryptedMessage.toByteArray());
            System.out.println("Message déchiffré du client : " + message);

            // Envoyer le message déchiffré au client
            out.writeUTF(message);

            clientSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RSAServer server = new RSAServer(512);
        server.startServer();
    }
}