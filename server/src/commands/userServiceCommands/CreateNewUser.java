package commands.userServiceCommands;

import managers.DML.DataBaseManager;
import commands.baseCommands.Command;
import models.users.User;

import java.io.Serial;
import java.io.Serializable;

public class CreateNewUser extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 112L;
    public String execute(User user, DataBaseManager dataBaseManager) {
        dataBaseManager.insertUser(user.getLogin(), user.getPassword(), user.getName());
        return "The account has been successfully created!";
    }
}
