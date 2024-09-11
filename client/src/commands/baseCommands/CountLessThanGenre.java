package commands.baseCommands;


import exceptions.ParameterException;
import parameters.CommandValidator;
import parameters.TypeParameter;

import java.io.Serial;
import java.io.Serializable;


public class CountLessThanGenre extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;

    public CountLessThanGenre(String parameter) {
        super(TypeParameter.GENRE, parameter);
    }

    public void executeReadParameters() throws ParameterException {
        parameter = CommandValidator.Validation(getTypeParameter(), getStringParameter());
    }
}

