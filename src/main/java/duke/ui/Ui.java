package duke.ui;

/**
 * Ui class which formatting of texts
 */
public class Ui {

    /**
     * Return text with formatting/decorations
     * @param message to be displayed
     * @return formatted text
     */
    public static String displayMessage(String message) {
        return message;
    }

    /**
     * Return welcome text
     * @return welcome text
     */
    public static String displayWelcomeMessage() {
        return Ui.displayMessage("Hello from Duke!\nWhat can I do for you?");
    }

    /**
     * Return text when loading error is encountered when reading from hard disk
     * @return error text
     */
    public static String showLoadingError() {
        return Ui.displayMessage("An error was encountered when loading from the provided filepath.");
    }

    /**
     * Return message when loading error is encountered when writing to hard disk
     * @return error text
     */
    public static String showSavingError() {
        return Ui.displayMessage("An error was encountered when saving to the provided filepath.");
    }
}
