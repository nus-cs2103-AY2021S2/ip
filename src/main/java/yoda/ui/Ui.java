package yoda.ui;

import java.util.Scanner;

/**
 * Ui class to handle interactions with the user.
 */
public class Ui {
    /** Divider to divide between successive instructions */
    private static final String DIVIDER = "--------------------------------";
    /** Valid inputs that are available to the user */
    private static final String HELP_LIST = "HELP!";
    /** Scanner to scan user input */
    private Scanner s;

    /**
     * Creates a Ui object.
     */
    public Ui() {
        s = new Scanner(System.in);
    }

    /**
     * Prints the divider.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Greets the user when the Yoda chatbot is started up.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you?");
    }

    /**
     * Reads the input provided by the user on the command line.
     * @return Input provided by the user.
     */
    public String readInput() {
        return s.nextLine();
    }

    /**
     * Bids farewell to the user after user is done using the Yoda chatbot.
     */
    public void exit() {
        System.out.println("Cya!");
    }

    /**
     * Shows the user the valid commands available to them.
     */
    public void showHelp() {
        System.out.println(HELP_LIST);
    }
}
