package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    /**
     * Initializes the required object for an Ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads a line of input entered by user.
     *
     * @return String containing user input
     */
    public String nextUserInput() {
        return sc.nextLine().trim();
    }

    /**
     * Terminates the functioning Ui Object.
     */
    public void close() {
        sc.close();
    }

    /**
     * Prints a partition line.
     */
    public void printPartitionLine() {
        System.out.println("    ---------------------------");
    }

    /**
     * Prints the greeting message.
     */
    public void printGreeting() {
        printPartitionLine();
        String logo = "    __  _____ _  ___   ___   _ ___\n" +
                "    \\ \\/ /_ _| \\| \\ \\ / / | | | __|\n" +
                "     >  < | || .` |\\ V /| |_| | _|\n" +
                "    /_/\\_\\___|_|\\_| |_|  \\___/|___|\n";
        System.out.println("    Hi there! Welcome to\n" + logo);
        System.out.println("    What can I do for you today?");
        printPartitionLine();
    }

    /**
     * Prints the farewell message.
     */
    public void printFarewell() {
        printPartitionLine();
        System.out.println("    Goodbye. Have a nice day!!");
        printPartitionLine();
    }

    /**
     * Prints a error message after formatting.
     *
     * @param message Content of the error message.
     */
    public void printErrorMessage(String message) {
        printPartitionLine();
        System.out.println("    OOPS!!! " + message);
        printPartitionLine();
    }

    /**
     * Prints a feedback message after user adds a task.
     *
     * @param task  Task added by user.
     * @param tasks List of tasks the task was added to.
     */
    public void printAddTaskReport(Task task, TaskList tasks) {
        printPartitionLine();
        System.out.println("    Got it. I've added this task");
        System.out.println("        " + task.toString());
        printTaskCount(tasks);
        printPartitionLine();
    }

    /**
     * Prints a message containing the total number of tasks.
     *
     * @param tasks List of all the tasks.
     */
    public void printTaskCount(TaskList tasks) {
        System.out.println("    Now you have " + tasks.getTaskCount() + " in the list.");
    }

    /**
     * Prints a feedback message after user marks a task as done.
     *
     * @param task    Task marked as done.
     * @param wasDone If the task was already marked as done.
     */
    public void printMarkTaskAsDoneMessage(Task task, boolean wasDone) {
        printPartitionLine();
        if (wasDone) {
            System.out.println("    You have already completed this task:");
        } else {
            System.out.println("    Congratulations! You have completed this task:");
        }
        System.out.println("        " + task.toString());
        printPartitionLine();
    }

    /**
     * Prints a feedback message after user deletes a task.
     *
     * @param task  Task deleted by user.
     * @param tasks List of tasks the task was deleted from.
     */
    public void printDeleteTaskMessage(Task task, TaskList tasks) {
        printPartitionLine();
        System.out.println("    Noted. This task has been removed:");
        System.out.println("        " + task.toString());
        printTaskCount(tasks);
        printPartitionLine();
    }

    /**
     * Prints all the tasks after formatting.
     *
     * @param tasks List of all the tasks.
     */
    public void printAllTasks(TaskList tasks) {
        printPartitionLine();
        if (tasks.isEmpty()) {
            System.out.println("    It seems like there is nothing in your list.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 1; i <= tasks.getTaskCount(); ++i) {
                System.out.println("    " + i + "." + tasks.getTask(i - 1).toString());
            }
        }
        printPartitionLine();
    }

}
