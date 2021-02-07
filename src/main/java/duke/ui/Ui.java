package duke.ui;

/**
 * Represents the UI of Duke. Provides methods to print to screen.
 */
public class Ui {

    private void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void printWelcomeMsg() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String msg = logo +
                "Hello! I am duke\n" +
                "What can I do for you?\n";

        print(msg);
    }

    public void print(String msg) {
        printLine();
        msg.lines().forEach(line -> System.out.printf("\t%s\n", line));
        printLine();
    }

    public String getDefaultErrorMessage() {
        return "An unknown error occurred. Please check the help manual for correct " +
                "command parameters. You can type 'help' to access the manual.";
    }
}
