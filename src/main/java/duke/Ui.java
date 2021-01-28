package duke;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "--------------------------------\n";

    /**
     * Prints out the message in the duke.Duke user interface format.
     *
     * @param str the string to be printed out
     */
    public static String printFormatMessage(String str) {
        return HORIZONTAL_LINE + str + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Prints out welcome message in the beginning.
     */
    public static String welcome() {
        return "Hello! I'm duke.Duke\n" + "What can I do for you?";
    }

    /**
     * Prints out welfare message in the end.
     */
    public static String welfare() {
        return "Bye. Hope to see you again soon!";
    }


}
