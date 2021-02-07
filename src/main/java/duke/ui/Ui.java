package duke.ui;

import duke.taskList.TaskList;
import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    private final static String borders = "________________________________________________";
    private final static String logo = "     __      _     ____                  _\n"
            + "    /  \\    | |   |  __| _____  ____    | |\n"
            + "   / /\\ \\   | | __| |__ |  ___|/ __ \\   | |\n"
            + "  / /__\\ \\  | ||__   __|| |   / ____/ __| |\n"
            + " / ______ \\ | |   | |   | |   \\ \\___ / _  |\n"
            + "/_/      \\_\\|_|   |_|   |_|    \\____|\\____|\n";

    Scanner sc;
    StringBuilder sb;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the next line of input as a string
     * @return a string of the input
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Checks whether the next line is available.
     * @return a boolean value on whether scanner contains a next line.
     */
    public boolean canRead() {
        return sc.hasNextLine();
    }

    /**
     * Runs when UI starts up, printing the logo and a greeting.
     */
    public void start() {
        System.out.println(logo + "\nHello Master. Nice to meet you, my name is Alfred.\n" +
                "How may I be of service, Master?");
    }

    /**
     * Output message when closing the bot
     */
    public static String goodBye() {
        String message = "Have a good day, Master. Take care.";
        formatAndPrintType1(message);
        return message;
    }

    /**
     * Output message when marking the completion of a task
     * @param task, the task that is to be marked done
     */
    public static String taskDone(Task task) {
        String message = "Well done, Master! I've marked this task as done:\n" +
                task.toString();
        formatAndPrintType1(message);
        return message;
    }

    /**
     * Output message when a task is deleted
     * @param task, the task to be deleted from the list
     */
    public static String deletedTask(Task task) {
        String message = "Understood Master. I've removed this task from the list:\n" +
                task.toString();
        formatAndPrintType2a(message);
        return message;
    }

    /**
     * Output message when a task is added
     * @param task, the task that is added into the list.
     */
    public static String addedTask(Task task) {
        String message = "As requested, I've added a new task for you, Master:\n" +
                task.toString();

        formatAndPrintType2a(message);
        return message;
    }

//
//    /**
//     * Prints message for number of tasks left in list.
//     * @param tasks, the current taskList object containing all the tasks
//     */
//    public static String getTaskListSize(TaskList tasks) {
//        String message;
//        if (tasks.getSize() <= 1) {
//            message = "You have " + tasks.getSize() + " task left in the list, Master.";
//        } else {
//            message = "You have " + tasks.getSize() + " tasks left in the list, Master.";
//        }
//        formatAndPrintType2b(message);
//        return message;
//    }

    /**
     * Formats the message fully with upper
     * and lower borders while printing out the message
     * @param message, the message to be printed
     */
    public static void formatAndPrintType1(String message) {
        System.out.println(borders);
        System.out.println(message);
        System.out.println(borders + "\n");
    }

    /**
     * Formats the message fully with UPPER borders only
     * and prints out the message
     * @param message, the message to be printed
     */
    public static void formatAndPrintType2a(String message) {
        System.out.println(borders);
        System.out.println(message);
    }

    /**
     * Formats the message fully with LOWER borders only
     * and prints out the message
     * @param message, the message to be printed
     */
    public static void formatAndPrintType2b(String message) {
        System.out.println("\n" + message);
        System.out.println(borders + "\n");
    }
}