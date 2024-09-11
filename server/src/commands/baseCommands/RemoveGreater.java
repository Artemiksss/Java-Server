package commands.baseCommands;

import managers.DML.DataBaseManager;
import models.users.PasswordHashing;
import models.users.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.stream.Collectors;

public class RemoveGreater extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 11L;

    public String execute(User user, DataBaseManager dataBaseManager) throws NullPointerException {
        String result = "";
        try {
            Long id = dataBaseManager.getUserId(user.getLogin(), user.getPassword());
            dataBaseManager.deleteMusicBandGreater(id, Long.parseLong(parameter.getParameter()));
            musicBands.updateCollection(dataBaseManager.updateCollection());
            result = musicBands.getMusicBands().keySet().stream()
                    .map(newKey -> "User ID: " + musicBands.getMusicBands().get(newKey).getUserId() + "; Music band: " + musicBands.getMusicBands().get(newKey).getName() + " | Key: " + newKey)
                    .collect(Collectors.joining("\n"));
            return "Collection: " + "\n" + result;
        } catch (InputMismatchException e) {
            System.out.println("The key was entered incorrectly!");
        } catch (NumberFormatException e) {
            System.out.println("The parameter was entered incorrectly!");
        }
        return "Command execution error!";
    }

    @Override
    public String toString() {
        return "Class name: RemoveGreater" + " - / " + new Date().toString() + " /";
    }

}
