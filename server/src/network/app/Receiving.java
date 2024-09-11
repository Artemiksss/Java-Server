package network.app;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Receiving {
    private final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
    public byte[] receiving(DatagramSocket serverSocket, DatagramPacket receivePacket) throws ExecutionException, InterruptedException {
        fixedThreadPool.submit(() -> {
            try {
                serverSocket.receive(receivePacket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).get();
        return receivePacket.getData();
    }
}
