package duke;

import java.util.Scanner;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Ui class is a class that handles all IO aspects in Duke.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor method for Ui class.
     */
    public Ui() {
        this.sc = new Scanner(System.in);

    }

    public void printHorizontalLine() {
        System.out.println("_____________________________________");
    }

    /**
     * Shows Duke's welcome message.
     */
    public void showWelcome() {
        printHorizontalLine();
        System.out.println("Hello! I'm Juke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }


    public String readCommand() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Error loading file");
    }

    /**
     * Shows the error message from exception.
     * @param error
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     *
     * @param message
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Prints the task added
     * @param task Task to be added.
     */
    public void printTaskAdded(Task task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
    }

    /**
     * Prints the tasks in the list.
     * @param taskList The list of tasks.
     */
    public void printNoOfItems(TaskList taskList) {
        int num = taskList.getSize();
        if (num == 1) {
            System.out.printf("Now you have %d task in the list.%n", num);
        } else {
            System.out.printf("Now you have %d tasks in the list.%n", num);
        }
    }

    /**
     * Prints the task marked as done.
     * @param task The task to be marked as done.
     */
    public void printTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.toString());
    }

    /**
     * Prints the task to be removed.
     * @param task The task to be removed.
     */
    public void printTaskRemoved(Task task) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task.toString());
    }

    public void printBye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public void printFoundTasks() {
        System.out.println("    Here are the matching tasks in your list:");
    }

    public Scanner getSc() {
        return this.sc;
    }

}
