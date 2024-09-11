package commands.baseCommands;


import exceptions.ParameterException;
import parameters.CommandValidator;
import parameters.TypeParameter;

import java.io.Serial;
import java.io.Serializable;


public class Show extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 16L;

    public Show() {
        super(TypeParameter.NONE, null);
    }

    public void executeReadParameters() throws ParameterException {
        parameter = CommandValidator.Validation(getTypeParameter(), getStringParameter());
    }
}
