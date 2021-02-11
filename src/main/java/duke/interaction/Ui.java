package duke.interaction;

import duke.common.DukeString;

/**
 * A class that handles the output to user.
 */
public class Ui {
    private Ui() {
    }

    /**
     * Prints the given message with a separator.
     *
     * @param msg the message to be printed.
     */
    public static void printOut(final String msg) {
        System.out.println(DukeString.SEPARATOR + msg + DukeString.SEPARATOR);
    }

    /**
     * Prints the given error with a special separator.
     *
     * @param msg the message to be printed.
     */
    public static void printErr(final String msg) {
        System.out.println(DukeString.SEPARATOR_ERR + msg + DukeString.SEPARATOR_ERR);
    }
}
