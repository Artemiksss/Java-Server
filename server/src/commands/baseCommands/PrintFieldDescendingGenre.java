package commands.baseCommands;

import managers.DML.DataBaseManager;
import models.users.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;

public class PrintFieldDescendingGenre extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 10L;

    public String execute(User user, DataBaseManager dataBaseManager) {
        String result = musicBands.getMusicBands().values().stream()
                .map(band -> {
                    if (band.getGenre() == null) {
                        throw new NullPointerException("Genre is null for music band. \n" + this.toString());
                    }
                    return band.getGenre().toString();
                })
                .sorted()
                .collect(Collectors.joining("\n"));
        return result;
    }

    @Override
    public String toString(){
        return "Class name: PrintFieldDescendingGenre" + " - / " + new Date().toString() + " /";
    }

}
