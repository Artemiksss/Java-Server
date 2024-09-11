package network.response;

import models.users.CheckingResponse;
import network.connectionWithServer.ServerConnect;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.DatagramPacket;

public class ServerResponse implements Serializable {
    public void response() throws IOException, ClassNotFoundException {
        byte[] receiveData = new byte[32768];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        ServerConnect.getDatagramSocket().receive(receivePacket);
        ByteArrayInputStream byteStream = new ByteArrayInputStream(receivePacket.getData());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteStream);
        Response receivedResponse = (Response) objectInputStream.readObject();
        System.out.println(CheckingResponse.checkingResponse(receivedResponse.getMessage()));

        ServerConnect.disconnect();
    }
}
