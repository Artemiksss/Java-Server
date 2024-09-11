package parser;

import commands.auxiliaryCommands.InfoAboutIdMusicBands;
import commands.auxiliaryCommands.InfoAboutKeyCollection;
import commands.baseCommands.*;
import commands.specialCommands.ExecuteScript;
import exceptions.ConsoleOutputErrorException;
import interfaces.Reader;

public class Parser {
    private final Reader scanner;
    private String command;
    private boolean flag = true;

    public Parser(){
        this.scanner = new ManualInput();
    }
    public Command start(){
        try {
            System.out.println("Enter the command:");
            command = scanner.nextLine();
            return parsing(command);
        } catch (ConsoleOutputErrorException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Command parsing(String command) throws ConsoleOutputErrorException {
        switch (command.split(" ")[0]) {
            case "keys":
                return new InfoAboutKeyCollection();
            case "id":
                return new InfoAboutIdMusicBands();
            case "help":
                return new Help();
            case "info":
                return new Info();
            case "show":
                return new Show();
            case "insert":
                if (command.split(" ").length > 1){
                    return new InsertNewElement(command.split(" ")[1]);
                }else {
                    System.out.println("The command was entered incorrectly");
                    System.out.println("Please enter the command: insert _____key_____");
                }
                return null;
            case "update":
                if (command.split(" ").length > 1){
                    return new Update(command.split(" ")[1]);
                }else {
                    System.out.println("The command was entered incorrectly");
                    System.out.println("Please enter the command: update _____id_____");
                }
                return null;
            case "remove_key":
                if (command.split(" ").length > 1){
                    return new RemoveKey(command.split(" ")[1]);
                }else {
                    System.out.println("The command was entered incorrectly");
                    System.out.println("Please enter the command: remove_key _____key_____");
                }
                return null;
            case "clear":
                return new Clear();
            case "execute_script":
                if (command.split(" ").length > 1){
                    return new ExecuteScript(command.split(" ")[1]);
                }else {
                    System.out.println("The command was entered incorrectly");
                    System.out.println("Please enter the command: execute_script _____path_____");
                }
                return null;
            case "exit":
                return new Exit();
            case "remove_greater":
                return new RemoveGreater();
            case "replace_if_greater":
                if (command.split(" ").length > 1){
                    return new ReplaceIfGreater(command.split(" ")[1]);
                }else {
                    System.out.println("The command was entered incorrectly");
                    System.out.println("Please enter the command: replace_if_greater _____key_____");
                }
                return null;
            case "remove_lower_key":
                if (command.split(" ").length > 1){
                    return new RemoveLowerKey(command.split(" ")[1]);
                }else {
                    System.out.println("The command was entered incorrectly");
                    System.out.println("Please enter the command: remove_lower_key _____key_____");
                }
                return null;
            case "count_less_than_genre":
                if (command.split(" ").length > 1){
                    return new CountLessThanGenre(command.split(" ")[1]);
                }else {
                    System.out.println("The command was entered incorrectly");
                    System.out.println("Please enter the command: count_less_than_genre _____genre_____");
                }
                return null;
            case "filter_starts_with_name":
                if (command.split(" ").length > 1){
                    return new FilterStartsWithName(command.split(" ")[1]);
                }else {
                    System.out.println("The command was entered incorrectly");
                    System.out.println("Please enter the command: filter_starts_with_name _____name_____");
                }
                return null;
            case "print_field_descending_genre":
                return new PrintFieldDescendingGenre();
            default:
                return null;
        }
    }
}

