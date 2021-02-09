package duke.ui;

/**
 * Ui class which handles interactions with the user
 */
public class Ui {

    /**
     * Displays a message properly formatted with borders
     *
     * @param message to be displayed
     */
    public static String displayMessage(String message) {
        // Function is current deprecated, may include decorators in future
        return message;
    }

    /**
     * Displays welcome message
     */
    public static String displayWelcomeMessage() {
        return Ui.displayMessage("Hello from Duke!\nWhat can I do for you?");
    }

    /**
     * Displays message when loading error is encountered when reading from hard disk
     */
    public static String showLoadingError() {
        return Ui.displayMessage("An error was encountered when loading from the provided filepath.");
    }

    /**
     * Displays message when loading error is encountered when writing to hard disk
     */
    public static String showSavingError() {
        return Ui.displayMessage("An error was encountered when saving to the provided filepath.");
    }
}
