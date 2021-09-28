package duke;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    /**
     * Prints out the message in the duke.Duke user interface format.
     *
     * @param str the string to be printed out
     */
    public static String printFormatMessage(String str) {
        return str + "\n";
    }

    /**
     * Prints out welcome message in the beginning.
     */
    public static String welcome() {
        return "Hello! I'm Duke\n" + "What can I do for you?";
    }

    /**
     * Prints out welfare message in the end.
     */
    public static String welfare() {
        return "Bye. Hope to see you again soon!";
    }


}
