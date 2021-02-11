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

    /**
     * Shows Duke's welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm Juke\n What can I do for you?";
    }


    public String readCommand() {
        return sc.nextLine();
    }

    public String showLoadingError(Exception e) {
        return e.getMessage();
    }

    /**
     * Shows the error message from exception.
     * @param error
     */
    public String showError(String error) {
        return error;
    }

    /**
     *
     * @param message
     */
    public String print(String message) {
        return message;
    }

    /**
     * Prints the task added
     * @param task Task to be added.
     */
    public String showTaskAdded(Task task) {
        return "Got it. I've added this task: \n" + task.toString();
    }

    /**
     * Prints the tasks in the list.
     * @param taskList The list of tasks.
     */
    public String showNoOfItems(TaskList taskList) {
        int num = taskList.getSize();
        if (num == 1) {
            return "Now you have " + num + " task in the list.\n";
        } else {
            return "Now you have " + num + " tasks in the list.\n";
        }
    }

    /**
     * Prints the task marked as done.
     * @param task The task to be marked as done.
     */
    public String showTaskDone(Task task) {
        return "Nice! I've marked this task as done: " + task.toString();
    }

    /**
     * Prints the task to be removed.
     * @param task The task to be removed.
     */
    public String showTaskRemoved(Task task) {
        return "Noted. I've removed this task: \n" + task.toString();

    }

    public String showBye() {
        return "    Bye. Hope to see you again soon!";
    }

    public String showFoundTasks() {
        return "    Here are the matching tasks in your list:";
    }

    public Scanner getSc() {
        return this.sc;
    }

}
