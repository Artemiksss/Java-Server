import commands.baseCommands.Command;
import managers.DML.DataBaseManager;
import models.users.User;
import network.*;
import network.managers.ServerManager;
import network.request.Request;
import network.app.Processing;
import network.response.Response;
import network.response.ResponseClient;

import java.io.IOException;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutionException;

public class Server {

    public static void main(String[] args) throws InterruptedException {
        try {
            DatagramSocket serverSocket = new Connection().connecting();
            ServerManager serverManager = new ServerManager(serverSocket);
            DataBaseManager dataBaseManager = new DataBaseManager();

            System.out.println("Server started...");

            try {
                while (!serverSocket.isClosed()) {
                    Request request = serverManager.processingTheReceivedRequest(serverSocket);
                    Command command = request.getCommand();
                    User user = request.getUser();
                    System.out.println("Received command: " + command.toString());
                    Processing.executingCommand(command, user, dataBaseManager, result -> {
                        try {
                            ResponseClient.response(new Response(result));
                        } catch (IOException | InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            } catch (SocketException | ExecutionException e) {
                System.out.println("The execution was aborted! The server has shut down.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
