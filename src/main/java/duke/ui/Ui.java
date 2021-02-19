package duke.ui;

import java.util.Scanner;

/**
 * Represents a user interface that interacts with the user.
 */
public class Ui {

    /**
     * Shows the introductory message.
     */
    public static String showWelcome() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from Duke!";
    }

    /**
     * Shows the exit information.
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Shows a line break.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Shows error messages.
     *
     * @param message Error message.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Reads user's command.
     *
     * @return User's full command.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}
