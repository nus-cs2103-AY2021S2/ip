package duke.ui;

/**
 * Represents the UI of Duke. Provides methods to print to screen.
 */
public class Ui {

    private void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints Duke's welcome message to console.
     */
    public void printWelcomeMsg() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String msg = logo
                + "Hello! I am duke\n"
                + "What can I do for you?\n";

        print(msg);
    }

    /**
     * Formats the given message with Duke's formatting and prints it to console.
     *
     * @param message the message to print
     */
    public void print(String message) {
        printLine();
        message.lines().forEach(line -> System.out.printf("\t%s\n", line));
        printLine();
    }

    /**
     * Returns the default error message that should be used for all exceptions.
     *
     * @return default error message
     */
    public String getDefaultErrorMessage() {
        return "An unknown error occurred. Please check the help manual for correct "
                + "command parameters. You can type 'help' to access the manual.";
    }
}
