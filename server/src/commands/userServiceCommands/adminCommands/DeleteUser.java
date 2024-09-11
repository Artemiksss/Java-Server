package commands.userServiceCommands.adminCommands;

import commands.baseCommands.Command;
import managers.DML.DataBaseManager;
import models.users.User;


import java.io.Serial;
import java.io.Serializable;

public class DeleteUser extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 333L;

    public String execute(User user, DataBaseManager dataBaseManager) {
        dataBaseManager.deleteUser(user.getId());
        return "User deleted!";
    }
}