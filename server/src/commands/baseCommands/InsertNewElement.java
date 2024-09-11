package commands.baseCommands;

import managers.DML.DataBaseManager;
import models.users.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.InputMismatchException;

public class InsertNewElement extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 9L;

    public String execute(User user, DataBaseManager dataBaseManager) throws NullPointerException {
        try {
            if (musicBands == null) {
                throw new NullPointerException("MusicBands is not initialized.\n" + this.toString());
            }
            for (Long currentKey : musicBands.getMusicBands().keySet()){
                if (currentKey == Long.parseLong(parameter.getKey())){
                    return "This key is busy, try to enter another key.";
                }
            }
            Long id = dataBaseManager.getUserId(user.getLogin(), user.getPassword());
            parameter.getMusicBand().setMusicBandId(dataBaseManager.insertUserMusicBands(id, parameter.getMusicBand(), Long.parseLong(parameter.getKey())));
            musicBands.put(Long.parseLong(parameter.getKey()), parameter.getMusicBandWithUserId(id));
            return "The item was added successfully";

        } catch (InputMismatchException e) {
            System.out.println("Ключ введен неверно!" + "\n" + new InsertNewElement().toString());
        }  catch (NumberFormatException e){
            System.out.println("The parameter was entered incorrectly!");
        }
        return "Command execution error!";
    }

    @Override
    public String toString() {
        return "Имя класса: InsertNewElement" + " - / " + new Date().toString() + " /";
    }
}

