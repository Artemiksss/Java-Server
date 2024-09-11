package network.request;

import network.app.Receiving;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutionException;

public class ReadingRequest implements Serializable {
    public byte[] reading(DatagramSocket serverSocket, DatagramPacket receivePacket) throws IOException, ExecutionException, InterruptedException {
        return new Receiving().receiving(serverSocket, receivePacket);
    }
}
