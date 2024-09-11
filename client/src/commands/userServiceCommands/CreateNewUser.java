package commands.userServiceCommands;

import commands.baseCommands.Command;
import parameters.TypeParameter;

import java.io.Serial;
import java.io.Serializable;

public class CreateNewUser extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 112L;
    public CreateNewUser() {
        super(TypeParameter.NONE, null);
    }

    public void executeReadParameters(){}
}
