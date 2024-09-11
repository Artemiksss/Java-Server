package commands.userServiceCommands;

import commands.baseCommands.Command;
import exceptions.ParameterException;
import parameters.CommandValidator;
import parameters.TypeParameter;

import java.io.Serial;
import java.io.Serializable;

public class CheckingUser extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 111L;
    public CheckingUser() {
        super(TypeParameter.USER, null);
    }

    public void executeReadParameters() throws ParameterException {
        parameter = CommandValidator.Validation(getTypeParameter(), getStringParameter());
    }
}
