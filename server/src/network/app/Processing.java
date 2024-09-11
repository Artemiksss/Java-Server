package network.app;

import commands.baseCommands.Command;
import exceptions.ConsoleOutputErrorException;
import managers.DML.DataBaseManager;
import models.users.User;

import java.io.IOException;
import java.util.function.Consumer;

public class Processing {
    public static void executingCommand(Command command, User user, DataBaseManager dataBaseManager, Consumer<String> callback) throws InterruptedException {
        Thread executThread = new Thread(() -> {
            try {
                String result = command.execute(user, dataBaseManager);
                callback.accept(result);
            } catch (ConsoleOutputErrorException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        executThread.start();
        executThread.join();
    }
}
