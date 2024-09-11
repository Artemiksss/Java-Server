package commands.baseCommands;

import exceptions.ConsoleOutputErrorException;
import managers.DML.DataBaseManager;
import models.users.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Help extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 7L;

    @Override
    public String execute(User user, DataBaseManager dataBaseManager) throws ConsoleOutputErrorException {
        try {
            String message = "help - output help for available commands" + "\n" +
                    "info - output information about the collection (type, initialization date, number of items, etc.) to the standard output stream." + "\n" +
                    "show - output all the elements of the collection in a string representation to the standard output stream" + "\n" +
                    "insert null - add a new element with the specified key"  + "\n" +
                    "update id - update the value of a collection item whose id is equal to the specified one" + "\n" +
                    "remove_key null - delete an item from the collection by its key" + "\n" +
                    "clear - clear the collection" + "\n" +
                    "save  - save the collection to a file" + "\n" +
                    "execute_script file_name - read and execute the script from the specified file. The script contains commands in the same form as they are entered by the user interactively."  + "\n" +
                    "exit - end the program (without saving to a file)" + "\n" +
                    "remove_greater - remove all items from the collection that exceed the specified limit" + "\n" +
                    "replace_if_greater null - replace the value by key if the new value is greater than the old one" + "\n" +
                    "remove_lower_key null - remove all items from the collection whose key is less than the specified one"  + "\n" +
                    "count_less_than_genre genre - print the number of elements whose genre field value is less than the specified one" + "\n" +
                    "filter_starts_with_name name - print the elements whose name field value starts with the specified substring" + "\n" +
                    "print_field_descending_genre - print the values of the genre field of all elements in descending order";
            return message;

        } catch (Exception e) {
            throw new ConsoleOutputErrorException("Ошибка вывода данных в консоль" + this.toString());
        }
    }

    @Override
    public String toString(){
        return "Имя класса: Help" + " - / " + new Date().toString() + " /";
    }

}
