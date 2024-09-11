package userInterface;

import commands.baseCommands.Command;
import commands.baseCommands.Exit;
import commands.userServiceCommands.CheckingUser;
import commands.userServiceCommands.CreateNewUser;
import commands.userServiceCommands.adminCommands.CheckAdminPassword;
import commands.userServiceCommands.adminCommands.DeleteUser;
import exceptions.ParameterException;
import models.users.Access;
import models.users.User;
import network.managers.ClientManager;
import network.request.Request;
import network.request.SendRequest;
import network.response.ServerResponse;
import network.connectionWithServer.ServerConnect;
import parser.ManualInput;
import parser.Parser;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

public class UserInterface {

    private final InetAddress SERVER_ADDRESS = InetAddress.getByName("localhost");
    private final int SERVER_PORT = 9777;
    private final String ADMIN = "eaf89db7108470dc3f6b23ea90618264b3e8f8b6145371667c4055e9c5ce9f52";

    public UserInterface() throws UnknownHostException {
    }

    public void startApplication(){
        try {
            System.out.println("Client start!");
            while (true) {
                System.out.println("Select an action: ");
                System.out.println("1. Log in to your account.");
                System.out.println("2. Create a new account.");
                System.out.println("3. Log in to your account as an administrator.");
                System.out.print("> ");
                Scanner scanner = new Scanner(System.in);
                String action = scanner.nextLine();
                if (Objects.equals(action, "1")) {
                    System.out.println("Enter your login: ");
                    System.out.print("> ");
                    String login = new ManualInput().nextLine();
                    System.out.println("Enter your password: ");
                    System.out.print("> ");
                    String password = new ManualInput().nextLine();
                    User user = new User(login, password);
                    send(new CheckingUser(), user);
                    if (User.getAccess() == Access.ALLOWED){
                        User.setAccess(Access.DENIED);
                        while (true) {
                            try {
                            Command commandWithParameters = getCommand(user);
                            if (commandWithParameters instanceof Exit){
                                break;
                            }
                                send(commandWithParameters, user);
                            } catch (IOException | ClassNotFoundException | NullPointerException ex) {
                                System.out.println("The server is unavailable!!");
                            } catch (ParameterException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    } else if (User.getAccess() == Access.DENIED){
                        System.out.println("Access is denied!");
                    }
                }
                if (Objects.equals(action, "2")) {
                    System.out.println("---------Creating a new account---------");
                    System.out.println("Enter your name: ");
                    System.out.print("> ");
                    String name = new ManualInput().nextLine();
                    System.out.println("Enter your login: ");
                    System.out.print("> ");
                    String login = new ManualInput().nextLine();
                    System.out.println("Enter your password: ");
                    System.out.print("> ");
                    String password = new ManualInput().nextLine();
                    User user = new User(name, login, password);
                    send(new CreateNewUser(), user);
                    System.out.println("----------------------------------------");
                }
                if (Objects.equals(action, "3")) {
                    System.out.println("--------------Admin Console--------------");
                    System.out.println("Enter admin password: ");
                    System.out.print("> ");
                    try {
                        String password = new ManualInput().nextLine();
                        send(new CheckAdminPassword(), new User("admin", password));
                        if (User.getAccess() == Access.ADMIN){
                            User.setAccess(Access.DENIED);
                            System.out.println("Admin logged in!");
                            while (true) {
                                System.out.println("Enter the ID of the user you want to delete: ");
                                System.out.print("> ");
                                String id = new ManualInput().nextLine();
                                if (Objects.equals(id, "exit")){
                                    break;
                                }
                                send(new DeleteUser(null), new User(Long.parseLong(id)));
                            }
                        }
                    } catch (NumberFormatException e){
                        System.out.println("Incorrect input format");
                    }

                }
                if (Objects.equals(action, "exit")) {
                    exit(1);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void send(Command commandWithParameters, User user) throws IOException, ClassNotFoundException {
        new SendRequest().sending(new Request(commandWithParameters, user), SERVER_ADDRESS, SERVER_PORT);
        new ServerResponse().response();
    }

    private Command getCommand(User user) throws ParameterException {
        Command commandWithParameters = null;
        do {
            Command command = new Parser().start();
            if (command instanceof Exit) {
                ServerConnect.disconnect();
                return new Exit();
            }
            if (command != null) {
                commandWithParameters = new ClientManager(command, user).handler(SERVER_ADDRESS, SERVER_PORT);
            }
        } while (commandWithParameters == null);
        return commandWithParameters;
    }
}
