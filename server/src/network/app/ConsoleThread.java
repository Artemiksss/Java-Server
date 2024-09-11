package network.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;

import static java.lang.System.exit;

public class ConsoleThread {
    public void run(DatagramSocket serverSocket){
        Thread consoleInputThread = new Thread(() -> {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    String input = reader.readLine();
                    if (input.equalsIgnoreCase("stop")) {
                        System.out.println("Logging out of the server...");
                        serverSocket.close();
                        break;
                    } else {
                        System.out.println("The wrong command. Type 'stop' to stop the server or 'save' to save everything to a file. ");
                    }
                }
                exit(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        consoleInputThread.start();
    }
}
