package commands.baseCommands;

import managers.DML.DataBaseManager;
import models.baseModels.MusicBand;
import models.users.PasswordHashing;
import models.users.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReplaceIfGreater extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 14L;

    public String execute(User user, DataBaseManager dataBaseManager) {
        try {
            if (parameter.getMusicBand() == null) {
                throw new NullPointerException("musicBand is not initialized.\n" + this.toString());
            }
            Long id = dataBaseManager.getUserId(user.getLogin(), user.getPassword());
            parameter.getMusicBand().setUserId(id);
            if (dataBaseManager.checkingKeyAndUser(id, Long.parseLong(parameter.getKey()))) {
                MusicBand oldValue = musicBands.getMusicBands().get(Long.parseLong(parameter.getKey()));
                if (oldValue != null) {
                    if (parameter.getMusicBand().getAlbumsCount() > oldValue.getAlbumsCount()) {
                        parameter.getMusicBand().setMusicBandId(oldValue.getMusicBandId());
                        dataBaseManager.updateUserMusicBandsKey(
                                Long.parseLong(parameter.getKey()), parameter.getMusicBand(), id);
                        if (Objects.equals(parameter.getMusicBand().getUserId(), id)) {
                            musicBands.put(Long.parseLong(parameter.getKey()), parameter.getMusicBand());
                            String result = "";
                            result = musicBands.getMusicBands().keySet().stream()
                                    .map(newKey -> "Name: " + musicBands.getMusicBands().get(newKey).getName() + " | Key: " + newKey)
                                    .collect(Collectors.joining("\n"));
                            return "The item has been successfully replaced!" + '\n' + "Collection" + "\n"+ result;
                        }
                    } else {
                        return "OldValue greater then newValue.";
                    }
                } else {
                    throw new NullPointerException("OldValue is not initialized.\n" + this.toString());
                }
            }else {
                return "The user does not have access to this element.";
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
        return "Class name: ReplaceIfGreater" + " - / " + new Date().toString() + " /";
    }

}
