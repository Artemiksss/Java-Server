package commands.baseCommands;

import managers.Collection.MusicBandCollectionManager;
import exceptions.ConsoleOutputErrorException;
import managers.DML.DataBaseManager;
import models.users.User;
import parameters.Parameters;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

public abstract class Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    static public MusicBandCollectionManager musicBands;
    protected Parameters parameter;
    abstract public String execute(User user, DataBaseManager dataBaseManager) throws ConsoleOutputErrorException, IOException;
}
