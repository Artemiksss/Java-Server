package exceptions;

public class ParameterException extends Exception {
    public ParameterException(String command) {
        super(command);
    }
}