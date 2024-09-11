package commands.baseCommands;


import exceptions.ParameterException;
import parameters.CommandValidator;
import parameters.TypeParameter;

import java.io.Serial;
import java.io.Serializable;


public class Clear extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public Clear() {
        super(TypeParameter.NONE, null);
    }

    public void executeReadParameters() throws ParameterException {
        parameter = CommandValidator.Validation(getTypeParameter(), getStringParameter());
    }
}
