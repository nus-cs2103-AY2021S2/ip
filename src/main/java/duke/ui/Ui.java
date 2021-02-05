package duke.ui;

import java.util.Scanner;

/**
 * The Ui class handles the printing of the user interface and reading of user input.
 */
public class Ui {

    /**
     * A customized greeting message to be printed when the program starts.
     *
     * @return The welcome message to be printed at the start.
     */
    public String greetingMessage() {
        return "Welcome back Max"
                + "\n"
                + "What can I do for you?\n";
    }

    /**
     * The message to be printed when the program exits without issues.
     */
    public String exitMessage() {
        return ("Bye. Hope to see you again soon!");
    }
}
