package network.managers;

import commands.baseCommands.Command;
import commands.specialCommands.ExecuteScript;
import exceptions.ParameterException;
import models.users.User;
import network.specialRequests.RequestingKeys;
import network.specialRequests.ScriptExecutionRequests;
import parameters.TypeParameter;

import java.io.IOException;
import java.net.InetAddress;
import java.text.ParseException;

public class ClientManager {
    private Command command;
    private User user;

    public ClientManager(Command command, User user) {
        this.command = command;
        this.user = user;
    }

    public Command handler(InetAddress serverAddress, int serverPort) throws ParameterException {
        if (command instanceof ExecuteScript) {
            commandExecuteScript(serverAddress, serverPort);
        } else {
            return defoultCommand();
        }
        return null;
    }

    private Command commandWithKey(InetAddress serverAddress, int serverPort) throws ParameterException {
        RequestingKeys.request(serverAddress, serverPort, user);
        command.executeReadParameters();
        return command;
    }

    private Command defoultCommand() throws ParameterException {
        command.executeReadParameters();
        return command;
    }

    private void commandExecuteScript(InetAddress serverAddress, int serverPort) {
        try {
            ScriptExecutionRequests.parseFile(serverAddress, serverPort, command, user);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
