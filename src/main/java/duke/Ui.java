package duke;

import java.util.Scanner;

/**
 * Interface that handles user inputs and prints programme outputs
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads one line of the user input
     * @return
     */
    public String readCommand() {
        return this.sc.nextLine().strip();
    }

    /**
     * Closes the interface. 
     * Once closed, the Ui will no longer read user inputs
     */
    public void close() {
        this.sc.close();
    }

    /**
     * Prints a message in the Duke format
     * @param message Message to be printed
     */
    public void printMessage(String message) {
        String newMessage = message.replaceAll("\n", "\n     ");
        System.out.println("    ____________________________________________________________\n\n" + "     " + newMessage
                + "\n" + "    ____________________________________________________________\n");
    }

    /**
     * Prints welcome message
     */
    public void showWelcome() {
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        printMessage(welcomeMessage);
    }

    /**
     * Prints error for loading save file
     */
    public void showLoadingError() {
        String loadingErrMessage = "OOPS!!! Was unable to load from save\nStarting a new task list...";
        printMessage(loadingErrMessage);
    }

    /**
     * Shows error for reading save file
     */
    public void showReadingError() {
        String loadingErrMessage = "OOPS!!! Was unable to parse save file\nStarting a new task list...";
        printMessage(loadingErrMessage);
    }

    /**
     * Shows goodbye message
     */
    public void showGoodbye() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        printMessage(goodbyeMessage);
    }
}
