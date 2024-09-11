package commands.userServiceCommands.adminCommands;

import commands.baseCommands.Command;
import managers.DML.DataBaseManager;
import models.users.PasswordHashing;
import models.users.User;


import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class CheckAdminPassword extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 444L;
    private final String ADMIN = "eaf89db7108470dc3f6b23ea90618264b3e8f8b6145371667c4055e9c5ce9f52";
    public String execute(User user, DataBaseManager dataBaseManager) {

        if (Objects.equals(PasswordHashing.hashPassword(user.getPassword()), ADMIN)) {
            return "Admin OK";
        }
        return "Access error";
    }
}
