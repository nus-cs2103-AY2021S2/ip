package duke.ui;

import java.util.Scanner;

/**
 * Ui class which handles interactions with the user
 */
public class Ui {
    private static final String BORDER = "-------------------------------------";

    private final Scanner scanner;

    /**
     * Constructor for Ui class. Intializes the scanner object for reading from std.in
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a message properly formatted with borders
     *
     * @param message to be displayed
     */
    public void displayMessage(String message) {
        System.out.println(Ui.BORDER);
        System.out.println(message);
        System.out.println(Ui.BORDER);
    }

    /**
     * Displays welcome message
     */
    public void displayWelcomeMessage() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.displayMessage("Hello from\n" + logo + "\nWhat can I do for you?");
    }

    /**
     * Displays message when loading error is encountered when reading from hard disk
     */
    public void showLoadingError() {
        this.displayMessage("An error was encountered when loading from the provided filepath.");
    }

    /**
     * Displays message when loading error is encountered when writing to hard disk
     */
    public void showSavingError() {
        this.displayMessage("An error was encountered when saving to the provided filepath.");
    }

    /**
     * Retrieves full command from the user
     *
     * @return raw user command
     */
    public String getUserCommand() {
        return this.scanner.nextLine().strip();
    }
}
