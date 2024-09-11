package commands.userServiceCommands.adminCommands;

import commands.baseCommands.Command;
import exceptions.ParameterException;
import parameters.CommandValidator;
import parameters.TypeParameter;

import java.io.Serial;
import java.io.Serializable;

public class DeleteUser extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 333L;

    public DeleteUser(String parameter) {
        super(TypeParameter.USER, parameter);
    }

    public void executeReadParameters() throws ParameterException {
        parameter = CommandValidator.Validation(getTypeParameter(), getStringParameter());
    }
}
