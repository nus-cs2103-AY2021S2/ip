package duke.ui;

/**
 * Ui class which stores all the basic UI stuff like welcome message.
 */

public class Ui {

    /**
     * Prints a welcome message.
     *
     * @return welcome message
     */
    public static String showWelcome() {
        String logo =
                  "_____        _        \n"
                + "|  _ \\ _  _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String toPrint = "Hello!\nWelcome to Duke!\n" + "What can I do for you?";
        assert !toPrint.isEmpty() : "Welcome screen should be printed.";
        return toPrint;
    }
    /**
     * Prints an exit message.
     *
     * @return exit message
     */
    public String exitDuke() {
        String toPrint = "Bye. Hope to see you again soon!";
        assert !toPrint.isEmpty() : "Something should be printed.";
        return toPrint;
    }
}

