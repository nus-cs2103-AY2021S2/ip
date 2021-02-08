package duke.ui;

import duke.exceptions.DukeException;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles I/O for program.
 *
 * Uses System.out and System.in exclusively.
 */
public class UiGui extends Ui {

    private final VBox dialogContainer;
    private static final String BORDER = "    ____________________________________________________________";
    private static final String INDENT = "     ";
    private static final Font FONT = new Font("Consolas", 11);

    /**
     * Creates reader for stdin
     */
    public UiGui(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }

    /**
     * Send exception for pretty printing.
     *
     * Exception message can be composed of multiple lines.
     *
     * @param e Exception.
     */
    public void showError(DukeException e) {
        String message = String.valueOf(e).strip();
        if (message.contains("\n")) {
            int index = message.indexOf('\n');
            String firstLine = message.substring(0, index);
            String[] restLines = message.substring(index + 1).split("\n");
            List<String> lines = Arrays.stream(restLines).map(s -> "  " + s).collect(Collectors.toList());
            showMessage("☹ OOPS!!! " + firstLine, lines);
        } else {
            showMessage("☹ OOPS!!! " + message);
        }
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setFont(FONT);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    private void print(String text) {
        dialogContainer.getChildren().add(getDialogLabel(text));
    }

    public void println(String text) {
        dialogContainer.getChildren().add(getDialogLabel(text + "\n"));
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
        print(logo);
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
            showMessage(
                    "Welcome to Duke!",
                    "",
                    "No existing tasks found.",
                    "A new task list has been created to get you started :)");
        } else {
            showMessage(
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
        showMessage(
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
        showMessage(
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
        showMessage(
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
    public void showMessage(String pre, List<String> lines, String post) {

        println(BORDER);
        if (!pre.isEmpty()) {
            println(INDENT + pre);
        }
        for (String line: lines) {
            println(INDENT + line);
        }
        if (!post.isEmpty()) {
            println(INDENT + post);
        }
        println(BORDER);
        println("");
    }

    /**
     * Pretty prints messages on stdout.
     *
     * Convenience function.
     *
     * @param pre Prefix message.
     * @param lines List of messages.
     */
    public void showMessage(String pre, List<String> lines) {
        showMessage(pre, lines, "");
    }

    /**
     * Pretty prints messages on stdout.
     *
     * Convenience function.
     *
     * @param lines List of messages.
     * @param post Postfix message.
     */
    public void showMessage(List<String> lines, String post) {
        lines.add(post);
        showMessage("", lines, post);
    }

    /**
     * Pretty prints messages on stdout.
     *
     * Convenience function.
     *
     * @param lines List of messages.
     */
    public void showMessage(List<String> lines) {
        showMessage("", lines, "");
    }

    /**
     * Pretty prints messages on stdout.
     *
     * Convenience function.
     *
     * @param lines Variable number of messages.
     */
    public void showMessage(String ... lines) {
        showMessage("", Arrays.asList(lines), "");
    }
}
