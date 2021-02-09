package duke.ui;

import java.util.Scanner;

/**
 * Ui class which handles interactions with the user
 */
public class Ui {

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
    public String displayMessage(String message) {
        // Function is current deprecated, may include decorators in future
        return message;
    }

    /**
     * Displays welcome message
     */
    public String displayWelcomeMessage() {
        return this.displayMessage("Hello from Duke!\nWhat can I do for you?");
    }

    /**
     * Displays message when loading error is encountered when reading from hard disk
     */
    public String showLoadingError() {
        return this.displayMessage("An error was encountered when loading from the provided filepath.");
    }

    /**
     * Displays message when loading error is encountered when writing to hard disk
     */
    public String showSavingError() {
        return this.displayMessage("An error was encountered when saving to the provided filepath.");
    }
}
