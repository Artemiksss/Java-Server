package network.connectionWithServer;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerConnect {
    private static DatagramSocket datagramSocket;

    public static DatagramSocket getDatagramSocket() {
        return datagramSocket;
    }

    public static DatagramSocket connect() throws SocketException {
        try {
            datagramSocket = new DatagramSocket();
            return datagramSocket;
        } catch (IOException e) {
            System.err.println("An error occurred while connecting to the server. " + e.getMessage());;
        }
        return null;
    }
    public static void disconnect() {
        try {
            datagramSocket.close();
        }catch (Exception e){
            System.err.println("The server is disabled.");
        }
    }
}
