package pason.ui;

import pason.exceptions.PasonException;

import java.util.Scanner;
import java.io.InputStream;

/**
 * Ui class for managing outputs and inputs.
 */
public class Ui {
    private final Scanner scanner;
    public static final String DIVIDER = "============================================================";

    /**
     * Initialises the Ui object and the scanner class.
     *
     * @param in  System.in for scanner
     */
    public Ui(InputStream in) {
        this.scanner = new Scanner(in);
    }

    /**
     * Reads user input for command.
     *
     * @return user input if exists or null if has no more input.
     */
    public String readCommand()
    {
        if(scanner.hasNext()) {
            return scanner.nextLine();
        } else {
            return null;
        }
    }

    /**
     * Displays the greeting message.
     */
    public void displayGreeting() {
        printMessage("Hey! It's PAson, ready to help :)\n"
                + "How can I help you today?");
    }

    /**
     * Prints message with formatted divider.
     *
     * @param message  String to be printed.
     * @return String formatted with top and bottom border.
     */
    public void printMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }
}
