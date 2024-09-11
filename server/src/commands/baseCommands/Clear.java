package commands.baseCommands;


import managers.DML.DataBaseManager;
import models.users.PasswordHashing;
import models.users.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Clear extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public String execute(User user, DataBaseManager dataBaseManager) throws NullPointerException {
        if (musicBands == null) {
            throw new NullPointerException("MusicBands is not initialized.\n" + this.toString());
        }
        long id = dataBaseManager.getUserId(user.getLogin(), user.getPassword());
        dataBaseManager.deleteAllMusicBandsByUserId(id);
        musicBands.updateCollection(dataBaseManager.updateCollection());
        return "The collection has been successfully cleared.";
    }

    @Override
    public String toString(){
        return "Class name: Clear" + " - / " + new Date().toString() + " /";
    }

}
