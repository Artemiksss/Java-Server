package commands.auxiliaryCommands;

import network.response.Response;
import network.response.ResponseClient;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;

public class CommandResultHandler implements Serializable {
    public static void sendUser(Response message){
        try {
            ResponseClient.response(message);
        } catch (IOException | ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
