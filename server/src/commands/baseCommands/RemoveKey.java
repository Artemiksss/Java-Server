package commands.baseCommands;

import commands.auxiliaryCommands.CheckCorrectKey;
import managers.DML.DataBaseManager;
import models.users.PasswordHashing;
import models.users.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class RemoveKey extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 12L;

    public String execute(User user, DataBaseManager dataBaseManager) {
        try {
            Long id = dataBaseManager.getUserId(user.getLogin(), user.getPassword());
            Long correctKey = CheckCorrectKey.checking(Long.parseLong(parameter.getKey()));
            if (dataBaseManager.checkingKeyAndUser(id, correctKey)) {
                dataBaseManager.deleteMusicBandsByUserId(id, correctKey);
                if (correctKey != -1 && Objects.equals(musicBands.getMusicBands().get(correctKey).getUserId(), id)) {
                    musicBands.remove(correctKey);
                    return "The operation was completed successfully";
                }
                if (!Objects.equals(musicBands.getMusicBands().get(correctKey).getUserId(), id)) {
                    return "The user does not have the right to delete this element!";
                }
            }
            return "The user does not have access to this element.";
        } catch (ClassCastException e) {
            System.out.println("Key not exist");
        } catch (InputMismatchException | ExecutionException | InterruptedException e) {
            System.out.println("The key was entered incorrectly!");
        }  catch (NumberFormatException e){
            System.out.println("The parameter was entered incorrectly!");
        }
        return "Command execution error!";
    }

    @Override
    public String toString(){
        return "Class name: RemoveKey" + " - / " + new Date().toString() + " /";
    }
}
