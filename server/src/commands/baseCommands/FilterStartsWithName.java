package commands.baseCommands;

import managers.DML.DataBaseManager;
import models.baseModels.MusicBand;
import models.users.User;


import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FilterStartsWithName extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 6L;

    public String execute(User user, DataBaseManager dataBaseManager) throws NullPointerException {
        try {
            String result = "";
            if (musicBands == null) {
                throw new NullPointerException("MusicBands is not initialized.\n" + this);
            }
            Long id = dataBaseManager.getUserId(user.getLogin(), user.getPassword());
            List<MusicBand> filteredBands = musicBands.getMusicBands().values().stream()
                    .filter(band -> band.getName().startsWith(parameter.getParameter()) && Objects.equals(band.getUserId(), id))
                    .toList();
            if (filteredBands.isEmpty()) {
                return "There are no such groups.";

            } else {
                result = filteredBands.stream()
                        .map(MusicBand::toString)
                        .collect(Collectors.joining("\n"));
            }
            return result;
        } catch (InputMismatchException e) {
            System.out.println("The name was entered incorrectly!");
        } catch (NumberFormatException e) {
            System.out.println("The parameter was entered incorrectly!");
        }
        return "Command execution error!";
    }

    @Override
    public String toString() {
        return "Class name: FilterStartsWithName" + " - / " + new Date().toString() + " /";
    }

}

