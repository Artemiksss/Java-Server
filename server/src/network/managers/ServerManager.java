package network.managers;

import commands.baseCommands.Command;
import managers.Collection.MusicBandCollectionManager;
import managers.DDL.Migration;
import managers.DML.DataBaseManager;
import network.request.CommandProcessing;
import network.request.ReadingRequest;
import network.request.Request;
import network.app.ConsoleThread;
import network.response.ResponseClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutionException;

public class ServerManager {
    private final ConsoleThread consoleThread = new ConsoleThread();
    private final DatagramSocket serverSocket;

    public ServerManager(DatagramSocket serverSocket) {
        this.serverSocket = serverSocket;
        consoleThread.run(serverSocket);
        Command.musicBands = new MusicBandCollectionManager();
        Command.musicBands.updateCollection(new DataBaseManager().updateCollection());
        Migration migration = new Migration();
        migration.createUsersSequences();
        migration.createUsersTables();
        migration.createMusicBandSequences();
        migration.createMusicBandTables();
    }

    public Request processingTheReceivedRequest(DatagramSocket serverSocket) throws IOException, ExecutionException,
            InterruptedException, ClassNotFoundException {
        byte[] receiveData = new byte[32768];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        new ResponseClient(serverSocket, receivePacket);
        byte[] receivedBytes = new ReadingRequest().reading(serverSocket, receivePacket);
        return new CommandProcessing().processing(receivedBytes);
    }
}
