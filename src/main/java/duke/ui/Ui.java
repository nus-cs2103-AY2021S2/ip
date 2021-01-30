package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Handles the input/output of the application.
 * Responsible for getting user input and printing messages to the console.
 */
public class Ui {
    private static final String DIVIDER = "------------------------------------------------------------";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    private final Scanner in;
    private final PrintStream out;

    /**
     * Creates a {code Ui} object with a standard input reader and a standard output writer.
     */
    public Ui() {
        in = new Scanner(System.in);
        out = System.out;
    }

    /**
     * Reads the user input string.
     *
     * @return full user input string
     */
    public String getUserInput() {
        return in.nextLine();
    }

    /**
     * Prints a horizontal line dividing separate messages.
     */
    public void printDivider() {
        out.println(DIVIDER);
    }

    /**
     * Prints the welcome greeting.
     */
    public void printGreeting() {
        String welcomeMsg = String.format("Hello! I'm\n%s\nWhat can I do for you?", LOGO);
        out.println(welcomeMsg);
    }

    /**
     * Prints the exit message.
     */
    public void printExitMessage() {
        out.println(MESSAGE_EXIT);
    }

    /**
     * Prints the message to be shown to the user to the console.
     *
     * @param messageForUser message to be shown to user
     */
    public void print(String messageForUser) {
        out.println(messageForUser);
    }
}
