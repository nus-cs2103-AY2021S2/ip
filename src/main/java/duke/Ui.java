package duke;

/**
 * The Ui class handles command-line based input from a user and
 * output to be displayed to the user of a running instance of Duke.
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Hello! I'm Marvin what can I do for you today?";

    /**
     * Constructs an instance of a Ui object.
     */
    public Ui() {
    }

    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }
}
