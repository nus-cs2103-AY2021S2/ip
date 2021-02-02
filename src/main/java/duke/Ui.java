package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the component of the Duke program
 * that deals with interactions with the user.
 */
public class Ui {

    protected static String lineSpacing = "____________________________________________________________";

    protected static String taskConfirmation = "Got it. I've added this task:\n";

    /**
     * Prints a greeting message for the user
     * when the Duke program starts.
     */
    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" + lineSpacing);
    }

    /**
     * Returns a String representing the user input.
     *
     * @return a String representing the user input.
     */
    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints a message informing the user
     * of the Task added to the TaskList
     * along with the total number of Tasks in the TaskList.
     *
     * @param t        the Task that was added to the TaskList.
     * @param taskList the TaskList in which the Task was added to.
     */
    public void printTaskAddedConfirmation(Task t, TaskList taskList) {
        int numTasks = taskList.getTasks().size();
        System.out.println(taskConfirmation + t
                + "\nNow you have " + numTasks
                + (numTasks < 2 ? " task " : " tasks ") + "in the list.\n"
                + lineSpacing);
    }

    /**
     * Prints the details of all the Tasks in the TaskList
     * in numerical order.
     *
     * @param taskList the TaskList containing the Tasks to be printed.
     */
    public void listTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list. Hooray!\n" + lineSpacing);
            return;
        }
        System.out.println("Here are the tasks in your list:");
        int num = 1;
        for (Task task : tasks) {
            System.out.println(num + "." + task);
            num++;
        }
        System.out.println(lineSpacing);
    }

    /**
     * Prints a message informing the user
     * of the Task that was marked as done.
     *
     * @param task the Task that was marked as done.
     */
    public void printTaskDoneConfirmation(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task
                + "\n" + lineSpacing);
    }

    /**
     * Prints a message informing the user
     * of the Task that was deleted from the TaskList
     * along with the remaining number of Tasks in that TaskList.
     *
     * @param taskList the TaskList from which the Task was deleted.
     * @param task     the Task to be deleted.
     */
    public void printTaskDeleteConfirmation(TaskList taskList, Task task) {
        ArrayList<Task> tasks = taskList.getTasks();
        System.out.println("Noted! I've removed this task:\n" + task
                + "\nNow you have " + tasks.size()
                + (tasks.size() == 1 ? " task " : " tasks ") + "in the list.\n"
                + lineSpacing);
    }

    /**
     * Prints the details of all the Tasks in the TaskList,
     * that matched a keyword input by the user,
     * in numerical order.
     *
     * @param taskList the TaskList containing the matched Tasks to be printed.
     */
    public void printMatchingTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("There are no matching tasks in your list. :(\n" + lineSpacing);
        } else {
            System.out.println("Here are the matching tasks in your list:");
            int num = 1;
            for (Task task : tasks) {
                System.out.println(num + "." + task);
                num++;
            }
            System.out.println(lineSpacing);
        }
    }

    /**
     * Prints a message that signals
     * the termination of the Duke program.
     */
    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!\n" + lineSpacing);
    }

    /**
     * Prints a message informing the user that
     * user input was invalid along with the error message.
     *
     * @param e the DukeException containing the details of the error.
     */
    public void printDukeExceptionMessage(DukeException e) {
        System.out.println("Duke has encountered an error: " + e.getMessage()
                + "\n" + lineSpacing);
    }
}
