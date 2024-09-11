package exceptions;

import commands.baseCommands.Command;

public class GenreException extends Exception {
    public GenreException(String command) {
        super(command);
    }
}