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
        return "Hello! I'm Duke\n" + "What can I do for you?";
    }
    /**
     * Prints an exit message.
     *
     * @return exit message
     */
    public String exitDuke() {
        return "Bye. Hope to see you again soon!";

    }
}
