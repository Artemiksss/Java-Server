package commands.baseCommands;

import exceptions.ParameterException;
import parameters.CommandValidator;
import parameters.TypeParameter;

import java.io.Serial;
import java.io.Serializable;

public class InsertNewElement extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 9L;

    public InsertNewElement(String parameter) {
        super(TypeParameter.MUSIC_BAND, parameter);
    }

    public void executeReadParameters() throws ParameterException {
        parameter = CommandValidator.Validation(getTypeParameter(), getStringParameter());
    }
}

