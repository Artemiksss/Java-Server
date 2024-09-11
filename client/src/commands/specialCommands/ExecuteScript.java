package commands.specialCommands;


import commands.baseCommands.Command;
import parameters.TypeParameter;

import java.io.Serial;
import java.io.Serializable;


public class ExecuteScript extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 4L;

    private final String fileName;

    public ExecuteScript(String fileName){
        super(TypeParameter.NONE, fileName);
        this.fileName = fileName;
    }

    public void executeReadParameters(){}

    public String getFileName() {
        return fileName;
    }
}
