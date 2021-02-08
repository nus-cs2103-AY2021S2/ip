package duke.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import duke.exceptions.DukeException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Handles I/O for program.
 *
 * General class, requires subclass to handle IO.
 * Consider overwriting showResponse to customize response as well.
 */
public abstract class Ui {

    private static final String BORDER = "    ____________________________________________________________";
    private static final String INDENT = "     ";

    public abstract String getUserInput(String inputPrompt, String inputHint);
    public abstract String getUserInputSecondary(String inputPrompt, String inputHint);
    public abstract boolean getUserConfirmation(String confirmationPrompt);
    public abstract void printError(DukeException e);
    public abstract void printCallback(String callbackText);
    public abstract void printResponse(String responseText);


    /**
     * Get user input from main prompt / window.
     *
     * @param inputPrompt user input prompt
     * @return string user input
     */
    public String getUserInput(String inputPrompt) {
        return getUserInput(inputPrompt, "");
    }

    /**
     * Get user input from separate prompt / window.
     *
     * @param inputPrompt user input prompt
     * @return string user input
     */
    public String getUserInputSecondary(String inputPrompt) {
        return getUserInputSecondary(inputPrompt, "");
    }

    /**
     * Prints user input as a line.
     *
     * @param callbackText user input
     */
    public void printCallbackLine(String callbackText) {
        printCallback(callbackText + "\n");
    }

    /**
     * Prints program response as a line.
     *
     * @param responseText program response
     */
    public void printResponseLine(String responseText) {
        printResponse(responseText + "\n");
    }

    /**
     * Prints main program logo
     */
    public void showWelcomeScreen() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printResponseLine(logo);
    }

    /**
     * Prints load screen upon successful task list load.
     *
     * If number of tasks is 0, message printed assumed no task list exists.
     * Otherwise, number of tasks is also printed.
     *
     * @param numTasks Number of tasks in loaded list.
     */
    public void showLoadingSuccess(int numTasks) {
        if (numTasks == 0) {
            showResponse(
                    "Welcome to Duke!",
                    "",
                    "No existing tasks found.",
                    "A new task list has been created to get you started :)");
        } else {
            showResponse(
                    "Welcome to Duke!",
                    "",
                    "Existing task list loaded successfully.",
                    "You have " + numTasks + " task" + (numTasks == 1 ? "" : "s") + " in your list.");
        }
    }

    /**
     * Prints load screen upon failure to write to file.
     *
     * For read-only file access by user - task list can still be loaded.
     * Indication to user that file cannot be written to, and serves as important
     * signal to user that changes are not preserved.
     *
     * @param numTasks Number of tasks in loaded list.
     */
    public void showFileWriteError(int numTasks) {
        showResponse(
                "Welcome to Duke!",
                "",
                "Warning: Destination file cannot be written to.",
                "Existing task list loaded, but changes will not be saved.",
                "",
                "You have " + numTasks + " task" + (numTasks == 1 ? "" : "s") + " in your list.");
    }

    /**
     * Prints load screen upon failure to load file.
     *
     * Occurs when directory cannot be created, or when file cannot be created
     * or read.
     */
    public void showFileLoadingError() {
        showResponse(
                "Welcome to Duke!",
                "",
                "Warning: Destination file cannot be created/read.",
                "New task list created, but changes will not be saved.");
    }

    /**
     * Prints load screen upon failure to load list.
     *
     * Occurs when file can be read, but the contents cannot be parsed
     * as a task list.
     */
    public void showLoadingError() {
        showResponse(
                "Welcome to Duke!",
                "",
                "Warning: Existing task list cannot be loaded.",
                "A new task list has been created.");
    }

    /**
     * Pretty prints messages on stdout.
     *
     * The most general method. Prefix and postfix string arguments are
     * provided for convenience, in particular for prefix to avoid concatenating
     * with potentially long list of lines.
     *
     * @param pre Prefix message.
     * @param lines List of messages.
     * @param post Postfix message.
     */
    public void showResponse(String pre, List<String> lines, String post) {

        printResponseLine(BORDER);
        if (!pre.isEmpty()) {
            printResponseLine(INDENT + pre);
        }
        for (String line: lines) {
            printResponseLine(INDENT + line);
        }
        if (!post.isEmpty()) {
            printResponseLine(INDENT + post);
        }
        printResponseLine(BORDER);
        printResponseLine("");
    }

    /**
     * Pretty prints messages on stdout.
     *
     * Convenience function.
     *
     * @param pre Prefix message.
     * @param lines List of messages.
     */
    public void showResponse(String pre, List<String> lines) {
        showResponse(pre, lines, "");
    }

    /**
     * Pretty prints messages on stdout.
     *
     * Convenience function.
     *
     * @param lines List of messages.
     * @param post Postfix message.
     */
    public void showResponse(List<String> lines, String post) {
        lines.add(post);
        showResponse("", lines, post);
    }

    /**
     * Pretty prints messages on stdout.
     *
     * Convenience function.
     *
     * @param lines List of messages.
     */
    public void showResponse(List<String> lines) {
        showResponse("", lines, "");
    }

    /**
     * Pretty prints messages on stdout.
     *
     * Convenience function.
     *
     * @param lines Variable number of messages.
     */
    public void showResponse(String ... lines) {
        showResponse("", Arrays.asList(lines), "");
    }
}
