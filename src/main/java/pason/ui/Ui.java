package pason.ui;

import java.util.Scanner;
import java.io.InputStream;

public class Ui {
    private final Scanner scanner;
    public static final String DIVIDER = "============================================================";

    public Ui(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public String readCommand()
    {
        if(scanner.hasNext()) {
            return scanner.nextLine();
        } else {
            return null;
        }
    }
    public void displayGreeting() {
        printMessage("Hey! It's PAson, ready to help :)\n"
                + "How can I help you today?");
    }

    public void printMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }
}
