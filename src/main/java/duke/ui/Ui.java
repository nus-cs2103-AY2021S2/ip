package duke.ui;

import duke.taskList.TaskList;
import duke.tasks.Task;

import java.util.Scanner;


/**
 * A class object that handles the User Interface aspect of the Duke program.
 */
public class Ui {
    private final static String borders = "________________________________________________";

    Scanner sc;
    StringBuilder sb;

    /**
     * Constructs a Ui object
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the next line of input as a string
     *
     * @return a string of the input
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Checks whether the next line is available.
     *
     * @return a boolean value on whether scanner contains a next line.
     */
    public boolean canRead() {
        return sc.hasNextLine();
    }

    /**
     * Runs when UI starts up, printing the logo and a greeting.
     */
    public static String startMessage() {
        return "Hello Master. Nice to meet you, my name is Alfred.\n"
                + "How may I be of service, Master?";
    }

    /**
     * Outputs a message when closing the bot
     */
    public static String goodBye() {
        String message = "Have a good day, Master. Take care.";
        formatAndPrintType(message);
        return message;
    }

    /**
     * Outputs a message when marking the completion of a task
     *
     * @param task the task that is to be marked done
     */
    public static String taskDone(Task task) {
        String message = "Well done, Master! I've marked this task as done:\n"
                       + task.toString();
        formatAndPrintType(message);
        return message;
    }

    /**
     * Outputs a message when a task is deleted
     *
     * @param task the task to be deleted from the list
     */
    public static String deletedTask(Task task, int size) {
        String message = "Understood Master. I've removed this task from the list:\n"
                       + task.toString()
                       + "\n\nYou have "
                       + Integer.toString(size)
                       + " tasks left in the list now, Master.";
        formatAndPrintType(message);
        return message;
    }

    /**
     * Outputs a message when a task is added
     *
     * @param task the task that is added into the list.
     */
    public static String addedTask(Task task, int size) {
        String message = "As requested, I've added a new task for you, Master:\n"
                       + task.toString()
                       + "\n\nYou have "
                       + Integer.toString(size)
                       + " tasks in the list now, Master.";


        formatAndPrintType(message);
        return message;
    }

    /**
     * Formats the message fully with upper
     * and lower borders while printing out the message
     *
     * @param message the message to be printed
     */
    public static void formatAndPrintType(String message) {
        System.out.println(borders);
        System.out.println(message);
        System.out.println(borders + "\n");
    }
}