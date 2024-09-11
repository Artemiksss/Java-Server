package network.request;

import commands.baseCommands.Command;
import models.users.User;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 88L;
    private Command command;
    private User user;
    public Request(Command command, User user) {
        this.command = command;
        this.user = user;
    }
    public Command getCommand() {
        return command;
    }

    public User getUser() {
        return user;
    }
}
