package duke.ui;

import duke.commands.CommandResult;

/**
 * Represents a generic user interface.
 */
public interface Ui {
    /**
     * Reads the command input by the user and returns it as a string.
     *
     * @return The user input.
     */
    String readCommand();

    /**
     * Displays the given CommandResult to the user.
     *
     * @param result The result to display.
     */
    void showCommandResult(CommandResult result);

    /**
     * Displays the given error message to the user.
     *
     * @param errMsg The error message to display.
     */
    void showError(String errMsg);

    /**
     * Displays a farewell to the user.
     */
    void showFarewell();

    /**
     * Displays a greeting to the user.
     */
    void showGreeting();

    /**
     * Displays a normal message to the user.
     *
     * @param msg The message to display.
     */
    void showMessage(String msg);
}
