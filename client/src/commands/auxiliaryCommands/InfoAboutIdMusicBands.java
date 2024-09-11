package commands.auxiliaryCommands;

import commands.baseCommands.Command;
import parameters.TypeParameter;

import java.io.Serial;
import java.io.Serializable;

public class InfoAboutIdMusicBands extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 542L;
    public InfoAboutIdMusicBands() {super(TypeParameter.NONE, null);}
    public void executeReadParameters(){}
}
