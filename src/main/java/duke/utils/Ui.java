package duke.utils;

public class Ui {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints the introduction message a user sees upon launching the application.
     */
    public void introduction() {
        System.out.println(logo);
        String welcomeMessage = "Hello! I'm Duke.";
        System.out.println(welcomeMessage);
    }

    /**
     * Prints the specified error message as an error message.
     *
     * @param errorMsg error message to print.
     */
    public void showError(String errorMsg) {
        System.err.println(errorMsg);
    }

    /**
     * Prints the specified message.
     *
     * @param msg message to print.
     */
    public void showMsg(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints the list of valid commands and types of input.
     */
    public void showHelp() {
        System.err.println("Valid commands include the following:");
        System.err.println("'todo', 'deadline', 'event', 'list', 'delete', 'bye'");
        System.err.println("Please start your input with one of the above commands!");
    }

    public void repeatInput() {
        System.out.println("Please enter your input again!");
    }
}
