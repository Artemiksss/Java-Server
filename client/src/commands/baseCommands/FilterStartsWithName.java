package commands.baseCommands;

import exceptions.ParameterException;
import parameters.CommandValidator;
import parameters.TypeParameter;

import java.io.Serial;
import java.io.Serializable;


public class FilterStartsWithName extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 6L;

    public FilterStartsWithName(String parameter) {
        super(TypeParameter.NAME, parameter);
    }

    public void executeReadParameters() throws ParameterException {
        parameter = CommandValidator.Validation(getTypeParameter(), getStringParameter());
    }
}

