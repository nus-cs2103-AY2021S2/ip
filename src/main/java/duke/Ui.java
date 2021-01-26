package duke;

import java.util.Scanner;

/**
 * Ui class to handle interaction and display of Duke Bot program interface in the command line
 */
public class Ui {
    private final Scanner sc;
    private static final String BORDER = "\t___________________________________\n";

    /**
     * Ui class constructor
     */
    public Ui() {
        greetUser();
        sc = new Scanner(System.in);
    }

    /**
     * Reads user input
     *
     * @return User input in String format
     */
    public String nextCommand() {
        return sc.nextLine();
    }

    private void greetUser() {
        String output = " Hello! I'm Duke\n" + "\t What can I do for you?";
        display(output);
    }

    /**
     * Displays response message back to the user in the command line
     */
    public void display(String message) {
        System.out.println(BORDER + "\t" + message + "\n" + BORDER);
    }
}
