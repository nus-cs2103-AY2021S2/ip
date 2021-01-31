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
        String line = "------------------------------------------";

        //introduction screen
        return line + "\nHello! I'm Duke\n" + "What can I do for you?\n" + line;
    }
}
