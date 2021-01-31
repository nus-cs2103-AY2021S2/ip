package duke.subfiles;

import java.util.Scanner;

/**
 * The Ui class manages interactions with the user, based on
 * user input.
 *
 * @author  arsatis
 * @version 1.1
 * @since   2021-01-26
 */
public class Ui {
    /** Scanner class used to read user input. */
    private Scanner sc;

    /**
     * Default constructor for the Ui class.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Greets the user upon execution of the program.
     */
    public void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Bids the user farewell before termination of the program.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    /**
     * Reads the user's input from the command line.
     *
     * @return The user's input formatted as a String object.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays a horizontal line on the GUI.
     */
    public void showLine() {
        System.out.println("__________");
    }

    /**
     * Displays a horizontal line of dots on the GUI.
     */
    public void showDots() {
        System.out.println("..........");
    }

    /**
     * Displays the error message produced by a command.
     *
     * @param s The error message produced by the command.
     */
    public void showError(String s) {
        System.out.println(s);
    }

}
