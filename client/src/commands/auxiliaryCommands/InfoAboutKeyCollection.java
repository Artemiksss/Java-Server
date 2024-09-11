package commands.auxiliaryCommands;

import commands.baseCommands.Command;
import parameters.TypeParameter;

import java.io.Serial;
import java.io.Serializable;

public class InfoAboutKeyCollection extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 17L;
    public InfoAboutKeyCollection() {super(TypeParameter.NONE, null);}
    public void executeReadParameters(){}
}
