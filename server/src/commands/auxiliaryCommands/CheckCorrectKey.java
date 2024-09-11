package commands.auxiliaryCommands;

import network.response.Response;
import network.response.ResponseClient;

import java.io.IOException;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.concurrent.ExecutionException;

import static commands.baseCommands.Command.musicBands;

public class CheckCorrectKey implements Serializable {
    static public Long checking(Long key) throws ExecutionException, InterruptedException {
        while (true) {
            try {
                if (musicBands.getMusicBands().get(key) != null) {
                    break;
                } else {
                    System.out.println("Key does not exist.");
                    ResponseClient.response(new Response("Key does not exist."));
                    return -1L;
                }
            } catch (InputMismatchException e) {
                System.out.println("The input error must be of type Integer (< 2147483647).");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return key;
    }
}
