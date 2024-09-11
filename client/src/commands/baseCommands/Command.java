package commands.baseCommands;

import exceptions.ParameterException;
import parameters.Parameters;
import parameters.TypeParameter;

import java.io.Serial;
import java.io.Serializable;

public abstract class Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    protected Parameters parameter;
    private final TypeParameter typeParameter;
    private final String stringParameter;

    public Command(TypeParameter typeParameter, String parameter) {
        this.typeParameter = typeParameter;
        this.stringParameter = parameter;
    }

    abstract public void executeReadParameters() throws ParameterException;

    public void setParameter(Parameters parameter) {
        this.parameter = parameter;
    }

    public TypeParameter getTypeParameter() {
        return typeParameter;
    }

    public String getStringParameter() {
        return stringParameter;
    }
}
