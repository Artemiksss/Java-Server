package commands.baseCommands;

import exceptions.ParameterException;
import parameters.CommandValidator;
import parameters.TypeParameter;

import java.io.Serial;
import java.io.Serializable;


public class RemoveKey extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 12L;

    public RemoveKey(String parameter) {
        super(TypeParameter.KEY, parameter);
    }

    public void executeReadParameters() throws ParameterException {
        parameter = CommandValidator.Validation(getTypeParameter(), getStringParameter());
    }
}
