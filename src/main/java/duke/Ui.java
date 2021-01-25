package duke;

import java.util.Scanner;

/**
 * Handles all commandline related interactions
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for Ui
     */
    public Ui() {
        welcomeUser();
        sc = new Scanner(System.in);
    }

    /**
     * Reads user input
     *
     * @return user input
     */
    public String fullCommand() {
        return sc.nextLine();
    }

    private void welcomeUser() {
        String output = "Hello! I'm Duke.Duke\n"
                + "\tWhat can I do for you?";
        response(output);
    }

    /**
     * Echoes out response for user's input
     *
     * @param output Message to be shown to user
     */
    public void response(String output) {
        String responseMsg = "\t____________________________________________________________\n"
                + "\t" + output + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(responseMsg);
    }

}
