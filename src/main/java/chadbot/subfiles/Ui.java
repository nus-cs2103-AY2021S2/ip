package chadbot.subfiles;

import java.util.Scanner;

/**
 * The Ui class manages interactions with the user, based on user input.
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

    /**
     * Returns a help page formatted as a String.
     *
     * @return A help page formatted as a String.
     */
    public String getHelp() {
        String helpPage = "Welcome to Chadbot!\n"
                + "Enter \"help\" to access this help page again.\n"
                + "--------------------------------------------------\n"
                + "Enter \"list\" to display the list of tasks you currently have.\n"
                + "--------------------------------------------------\n"
                + "Enter \"find <keyword>\" to find tasks containing the specified keyword.\n"
                + "--------------------------------------------------\n"
                + "Enter \"done <index>\" to mark a task at the specified index as done.\n"
                + "--------------------------------------------------\n"
                + "Enter \"delete <index>\" to delete a task at the specified index.\n"
                + "--------------------------------------------------\n"
                + "Enter \"sort\" to sort your list of tasks into alphabetical ordering.\n"
                + "Enter \"sort by type\" to sort your list of tasks by their type.\n"
                + "Enter \"sort by date\" to sort your list of tasks first by their type, then by their date.\n"
                + "--------------------------------------------------\n"
                + "Enter \"todo <description>\" to add a todo with the description into your list of tasks.\n"
                + "Enter \"deadline <description> /by <date>\" to add a deadline with the specified description"
                + " and date into your list of tasks.\n"
                + "Enter \"event <description> /at <date>\" to add an event with the specified description"
                + " and date into your list of tasks.\n"
                + "--------------------------------------------------\n"
                + "Enter \"bye\" to terminate the program.\n";
        return helpPage;
    }

}
