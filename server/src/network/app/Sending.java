package network.app;

import network.response.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class Sending {
    private static ForkJoinPool forkJoinPool = new ForkJoinPool();

    public void send(DatagramSocket serverSocket, DatagramPacket receivePacket, Response response) throws ExecutionException, InterruptedException {
        forkJoinPool.submit(() -> {
            try {
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(byteStream);
                out.writeObject(response);
                byte[] sendData = byteStream.toByteArray();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).get();
    }
}
