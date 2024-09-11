package commands.userServiceCommands.adminCommands;

import commands.baseCommands.Command;
import exceptions.ParameterException;
import parameters.CommandValidator;
import parameters.TypeParameter;

import java.io.Serial;
import java.io.Serializable;

public class CheckAdminPassword extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 444L;
    public CheckAdminPassword() {
        super(TypeParameter.ADMIN, null);
    }

    public void executeReadParameters() throws ParameterException {
        parameter = CommandValidator.Validation(getTypeParameter(), getStringParameter());
    }
}
