package chadbot.subfiles;

import java.util.Scanner;

/**
 * The Ui class manages interactions with the user, based on user input.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
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
        System.out.println("Welcome to Chadbot.\nWhat can I do for you?");
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
        String helpPage = "So you need help, eh?\n"
                + "--------------------------------------------------\n"
                + "Enter \"help\" to access this help page again.\n"
                + "Enter \"help /more\" to get access to additional commands supported by Chadbot.\n"
                + "--------------------------------------------------\n"
                + "Enter \"todo <description>\" to add a todo with the description into your list of tasks.\n"
                + "Enter \"deadline <description> /by <date>\" to add a deadline with the specified description"
                + " and date into your list of tasks.\n"
                + "Enter \"event <description> /at <date>\" to add an event with the specified description"
                + " and date into your list of tasks.\n"
                + "--------------------------------------------------\n"
                + "Enter \"list\" to display the list of tasks you currently have.\n"
                + "Enter \"list <date>\" to display the list of deadlines due and events happening on your"
                + " specified date.\n"
                + "--------------------------------------------------\n"
                + "Enter \"delete <index>\" to delete a task at the specified index.\n"
                + "--------------------------------------------------\n"
                + "Enter \"bye\" to terminate the program.\n";
        System.out.println(helpPage);
        return helpPage;
    }

    /**
     * Returns a second help page formatted as a String.
     *
     * @return A second help page formatted as a String.
     */
    public String getMoreHelp() {
        String helpPage = "What are you so desperately trying to find?\n"
                + "--------------------------------------------------\n"
                + "Enter \"help\" to access the previous help page.\n"
                + "Enter \"help /more\" to access this help page again.\n"
                + "--------------------------------------------------\n"
                + "Enter \"edit <index> /desc <description>\" to edit the description of a task at the specified"
                + " index.\n"
                + "Enter \"edit <index> /date <date>\" to edit the date of a deadline or event at the specified"
                + " index.\n"
                + "--------------------------------------------------\n"
                + "Enter \"done <index>\" to mark a task at the specified index as done.\n"
                + "--------------------------------------------------\n"
                + "Enter \"find <keyword>\" to find tasks containing the specified keyword.\n"
                + "--------------------------------------------------\n"
                + "Enter \"sort\" to sort your list of tasks into alphabetical ordering.\n"
                + "Enter \"sort /by type\" to sort your list of tasks by their type.\n"
                + "Enter \"sort /by date\" to sort your list of tasks first by their type, then by their date.\n"
                + "--------------------------------------------------\n"
                + "Enter \"stats\" to see the number of to-dos, deadlines, and events you currently have.\n";
        System.out.println(helpPage);
        return helpPage;
    }

}
