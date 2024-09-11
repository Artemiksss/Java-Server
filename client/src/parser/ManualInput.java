package parser;

import interfaces.Reader;

import java.util.Scanner;

public class ManualInput implements Reader {
    private static final Scanner userScanner = ConsoleScanner.scanning();

    @Override
    public String nextLine() {
        return userScanner.nextLine();
    }
}
