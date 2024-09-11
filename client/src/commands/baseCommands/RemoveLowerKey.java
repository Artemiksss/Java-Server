package commands.baseCommands;

import exceptions.ParameterException;
import parameters.CommandValidator;
import parameters.TypeParameter;

import java.io.Serial;
import java.io.Serializable;


public class RemoveLowerKey extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 13L;

    public RemoveLowerKey(String parameter) {
        super(TypeParameter.KEY, parameter);
    }

    public void executeReadParameters() throws ParameterException {
        parameter = CommandValidator.Validation(getTypeParameter(), getStringParameter());
    }
}
