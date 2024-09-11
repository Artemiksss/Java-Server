package commands.baseCommands;


import managers.DML.DataBaseManager;
import models.users.PasswordHashing;
import models.users.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Objects;

public class Update extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 17L;

    public String execute(User user, DataBaseManager dataBaseManager) {
        try {
            if (parameter.getMusicBand() == null) {
                throw new NullPointerException("newMusicBand is not initialized.\n" + this.toString());
            }
            Long key = 0L;
            for (Long currentKey : musicBands.getMusicBands().keySet()){
                if (musicBands.getMusicBands().get(currentKey).getMusicBandId().equals(Long.parseLong(parameter.getMusicBandId()))){
                    key = currentKey;
                    break;
                }
            }
            Long id = dataBaseManager.getUserId(user.getLogin(), user.getPassword());
            parameter.getMusicBand().setUserId(id);
            if (musicBands.getMusicBands().containsKey(key)) {
                if (dataBaseManager.checkingAvailabilityGroup(id, Long.parseLong(parameter.getMusicBandId()))){
                    dataBaseManager.updateUserMusicBandsId(Long.parseLong(parameter.getMusicBandId()), parameter.getMusicBand(), id);
                    if (Objects.equals(parameter.getMusicBand().getUserId(), id)) {
                        parameter.getMusicBand().setMusicBandId(musicBands.getMusicBands().get(key).getMusicBandId());
                        musicBands.put(key, parameter.getMusicBand());
                        return "An element with a id " + parameter.getMusicBandId() + " successfully updated.";
                    }
                }
                return "The user does not have access to this element or the element was not found.";
            } else {
                return "An element with a id " + parameter.getMusicBandId() + " not found in the collection.";
            }
        } catch (InputMismatchException e) {
            System.out.println("The key was entered incorrectly!");
        } catch (NumberFormatException e){
            System.out.println("The parameter was entered incorrectly!");
        }
        return "Command execution error!";
    }

    @Override
    public String toString() {
        return "Class name: Update" + " - / " + new Date().toString() + " /";
    }

}
