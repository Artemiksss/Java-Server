package network.specialRequests;

import commands.auxiliaryCommands.InfoAboutIdMusicBands;
import commands.auxiliaryCommands.InfoAboutKeyCollection;
import models.users.User;
import network.request.Request;
import network.request.SendRequest;
import network.response.ServerResponse;

import java.io.IOException;
import java.net.InetAddress;

public class RequestingMusicBandId {
    public static void request(InetAddress serverAddress, int serverPort, User user) {
        try {
            new SendRequest().sending(new Request(new InfoAboutIdMusicBands(), user), serverAddress, serverPort);
            new ServerResponse().response();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
