package duke.ui;

import java.util.Scanner;

import duke.task.Task;

/**
 * A class to handle interaction with users
 */
public class UI {
    static final String LINES = "----------------------------------------";
    private static Scanner sc = new Scanner(System.in);

    /**
     * Print out welcome message to user
     */
    public static void displayWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hi There! Greetings from \n" + logo);
        System.out.println(LINES + "\n" + " Good day! I'm Duke" + "\n" + " How can I help you today? " + "\n" + LINES);

    }

    /**
     * Print out bye message to user
     */
    public static void displayEndMessage() {
        System.out.println(LINES + "\n" + " Bye. Hope to see you again!" + "\n" + LINES);
    }

    /** Read next input from user
     * @return String
     */
    public static String readCommand() {
        return sc.nextLine();
    }

    /** Print out the task found in list
     * @param count
     * @param task
     */
    public void printTask(int count, Task task) {
        System.out.println((count + 1) + "." + task.toString());
    }

    /**
     * Display header for list method
     */
    public void printListHeader() {
        System.out.println("\nHere are the tasks in your list:");
    }

    public void printFindHeader() {
        System.out.println("\nHere are the matching tasks in your list:");
    }

    /**
     * Display dotted lines
     */
    public void displayLines() {
        System.out.println(LINES);
    }

    /** Print out message when task is added
     * @param t task
     * @param size
     */
    public void displayAddedTaskMessage(Task t, int size) {
        System.out.println(LINES + "\nGot it. I've added this task: \n\t" + t.toString() + "\n Now you have "
                + size + " tasks in your list \n" + LINES);
    }

    /**Print out message when task is deleted
     * @param t task
     */
    public static void displayDeletedTaskMessage(Task t) {
        System.out.println(LINES + "\nNice! I've removed this task: \n" + t.toString() + "\n" + LINES);
    }

    /** Print out message when task is done
     * @param t task
     */
    public void displayDoneTaskMessage(Task t) {
        System.out.println(LINES + "\nNice! I'll make this task as done: \n" + t.toString() + "\n" + LINES);
    }

    /** Print out exception messsage
     * @param e execption messsage
     */
    public void showError(String e) {
        System.out.println(e);
    }

}
