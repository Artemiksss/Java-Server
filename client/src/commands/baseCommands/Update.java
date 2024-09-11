package commands.baseCommands;

import exceptions.ParameterException;
import parameters.CommandValidator;
import parameters.TypeParameter;

import java.io.Serial;
import java.io.Serializable;


public class Update extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 17L;

    public Update(String parameter) {
        super(TypeParameter.ID_AND_MUSIC_BAND, parameter);
    }

    public void executeReadParameters() throws ParameterException {
        parameter = CommandValidator.Validation(getTypeParameter(), getStringParameter());
    }
}
