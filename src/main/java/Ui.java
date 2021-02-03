import javafx.scene.control.Label;

/**
 * Ui class for CS2103T iP. Handles printing of messages from the program.
 */

public class Ui {
    private final String LINES = "____________________________________________________";
    private final String INDENTATION = "     ";

    private final String TOP_LINE = LINES + "\n" + INDENTATION;
    private final String END_LINE = "\n" + LINES;

    /**
     * Prints supplied message within the 2 lines.
     * @param text Message to be printed.
     * @return Label with associated message.
     */
    public Label print(String text) {
        return new Label(TOP_LINE + text + END_LINE);
    }

    /**
     * Overloaded method for multiple line messages.
     * @param texts Multi line message stored in a String array.
     * @return Label with associated message.
     */
    public Label print(String[] texts) {
        String output = LINES + "\n";
        for (String text : texts) {
            output += INDENTATION + text + "\n";
        }
        output += LINES;
        return new Label(output);
    }

    /**
     * Prints error messages.
     * @param err Error to be printed.
     * @return Label with associated error message.
     */
    public Label printErr(String err) {
        return new Label(TOP_LINE + "☹ OOPS!!! " + err + END_LINE);
    }

    /**
     * Prints the welcome logo and text.
     * @return Label with welcome message.
     */
    public Label welcome() {
        String logo = " __          _        \n"
                + "|  _ \\ _  _| |  _ _ \n"
                + "| |  | | | | | |  |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|___/ \\_,_|_|\\_\\___|\n";
        String full = "Hello from\n" + logo + LINES + "\n     Hello! I'm Duke.\n"
                + "     What can I do for you?\n" + LINES;
        return new Label(full);
    }

    /**
     * Prints upon program termination.
     * @return Label with goodbye message.
     */
    public Label bye() {
        return new Label(TOP_LINE + "Bye. Hope to see you again soon!" + END_LINE);
    }

    /**
     * Prints when there is an IOException.
     * @return Label with IO error.
     */
    public Label ioException() {
        return printErr("An uncorrectable I/O error occurred");
    }
}