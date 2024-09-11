import userInterface.UserInterface;

import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws UnknownHostException {
        new UserInterface().startApplication();
    }
}