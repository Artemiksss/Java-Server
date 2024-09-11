package network.request;

import network.connectionWithServer.ServerConnect;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class SendRequest {
    public void sending(Request request, InetAddress serverAddress, int serverPort) throws IOException {
        ServerConnect.connect();
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(request);
        byte[] sendData = byteStream.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
        ServerConnect.getDatagramSocket().send(sendPacket);
    }
}
