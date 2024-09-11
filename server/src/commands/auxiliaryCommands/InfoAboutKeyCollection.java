package commands.auxiliaryCommands;

import commands.baseCommands.Command;
import managers.DML.DataBaseManager;
import models.users.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class InfoAboutKeyCollection extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 17L;

    public String execute(User user, DataBaseManager dataBaseManager) {
        String info = "";
        if (musicBands.getMusicBands().isEmpty()) {
            return "The collection is empty!";
        }
        for (Long key : musicBands.getMusicBands().keySet()) {
            if (Objects.equals(dataBaseManager.getUserId(user.getLogin(), user.getPassword()), musicBands.getMusicBands().get(key).getUserId()))
                info += "User ID: " + musicBands.getMusicBands().get(key).getUserId() + " | Key: " + key + " | Music band: " + musicBands.getMusicBands().get(key).getName() + "\n";
        }
        return info;
    }

    @Override
    public String toString() {
        return "Class name: InfoAboutKeyCollection" + " - / " + new Date().toString() + " /";
    }
}
