package commands.baseCommands;

import managers.DML.DataBaseManager;
import models.baseModels.MusicGenre;
import models.users.User;


import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class CountLessThanGenre extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;

    public String execute(User user, DataBaseManager dataBaseManager) throws NullPointerException {
        if (musicBands == null) {
            throw new NullPointerException("MusicBands is not initialized.\n" + this.toString());
        }
        if (parameter.getParameter() == null){
            return "Error, please try again!" + "\n" +
                    "Available genres: " + "\n" +
                    "-- POP" + "\n" +
                    "-- BLUES" + "\n" +
                    "-- ROCK" + "\n" +
                    "-- POST_ROCK" + "\n" +
                    "-- BRIT_POP";
        }
        long count = musicBands.getMusicBands().values().stream()
                .filter(band -> band.getGenre().compareTo(Objects.requireNonNull(MusicGenre.convert(parameter.getParameter()))) < 0)
                .count();
        return String.valueOf(count);
    }

    @Override
    public String toString(){
        return "Class name: CountLessThanGenre" + " - / " + new Date().toString() + " /";
    }

}

