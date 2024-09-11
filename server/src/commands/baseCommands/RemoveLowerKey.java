package commands.baseCommands;

import managers.DML.DataBaseManager;
import models.users.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.stream.Collectors;

public class RemoveLowerKey extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 13L;

    public String execute(User user, DataBaseManager dataBaseManager) {
        String result = "";
        boolean flag = false;
        for (Long currentKey : musicBands.getMusicBands().keySet()) {
            if (currentKey.equals(Long.parseLong(parameter.getKey()))) {
                flag = true;
                break;
            }
        }
        try {
            if (flag) {
                Long id = dataBaseManager.getUserId(user.getLogin(), user.getPassword());
                dataBaseManager.deleteMusicBandLower(id, Long.parseLong(parameter.getKey()));
                musicBands.updateCollection(dataBaseManager.updateCollection());
                result = musicBands.getMusicBands().keySet().stream()
                        .map(newKey -> "User ID: " + musicBands.getMusicBands().get(newKey).getUserId() + "; Music band: " + musicBands.getMusicBands().get(newKey).getName() + " | Key:  " + newKey)
                        .collect(Collectors.joining("\n"));
                return result;
            } else {
                return "The entered key is missing from the collection.";
            }
        } catch (InputMismatchException e) {
            System.out.println("The key was entered incorrectly!");
        } catch (NumberFormatException e) {
            System.out.println("The parameter was entered incorrectly!");
        }
        return "Command execution error!";
    }

    @Override
    public String toString() {
        return "Class name: RemoveLowerKey" + " - / " + new Date().toString() + " /";
    }

}
