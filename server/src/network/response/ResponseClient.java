package network.response;


import network.app.Sending;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutionException;

public class ResponseClient implements Serializable {
    @Serial
    private static final long serialVersionUID = 5L;
    private static DatagramSocket serverSocket;
    private static DatagramPacket receivePacket;
    public ResponseClient(DatagramSocket serverSocket, DatagramPacket receivePacket){
        ResponseClient.serverSocket = serverSocket;
        ResponseClient.receivePacket = receivePacket;
    }
    public synchronized static void response(Response response) throws IOException, ExecutionException, InterruptedException {
        new Sending().send(serverSocket, receivePacket, response);
    }
}
