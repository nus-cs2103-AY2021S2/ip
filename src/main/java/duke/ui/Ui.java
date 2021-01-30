package duke.ui;

import java.util.Scanner;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class Ui {
    public Ui() {}

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showGreeting();
    }

    private void showGreeting() {
        System.out.println("\t____________________________________________________________\n"
                + "\tHello! I'm Duke\n\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n");
    }

    /**
     * Prints the specified error message.
     *
     * @param message Message given by the error.
     */
    public void showError(String message) {
        System.out.println("\t____________________________________________________________\n"
                + message + "\t____________________________________________________________\n");
    }

    /**
     * Returns the user input as a string.
     *
     * @return user input string.
     */
    public String readCommand() {
        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }

    /**
     * Prints the full list of Task in TaskList, numbered in increasing order.
     *
     * @param tasks TaskList to be printed.
     */
    public void showTasks(TaskList tasks) {
        System.out.println("\t____________________________________________________________\n"
                + "\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.getTask(i).toString());
        }
        System.out.println("\t____________________________________________________________\n");
    }

    /**
     * Prints a message to indicate TaskList is empty.
     */
    public void showEmptyList() {
        System.out.println("\t____________________________________________________________\n"
                + "\tThere are no items in your list.\n"
                + "\t____________________________________________________________\n");
    }

    /**
     * Prints a message to indicate error when editing save file.
     */
    public void showIoError() {
        System.out.println("\t____________________________________________________________\n"
                + "\tError happened while trying to edit save file.\n"
                + "\t____________________________________________________________\n");
    }

    /**
     * Prints a message to indicate invalid date input.
     */
    public void showOutOfBoundsError() {
        System.out.println("\t____________________________________________________________\n"
                + "\tPlease enter the date (DD/MM/YYYY) with optional\n"
                + "\ttime (in 24 hours format) after \"/by\" for Deadline Tasks\n"
                + "\tor date with optional start and end time after \"/at\" \n"
                + "\tfor Event Tasks.\n"
                + "\t____________________________________________________________\n");
    }

    /**
     * Prints a message to indicate invalid date input format.
     */
    public void showDateTimeParseError() {
        System.out.println("\t____________________________________________________________\n"
                + "\tPlease enter in DD/MM/YYYY format (eg. 02/04/2000) for dates\n"
                + "\tand in 24 hour format (eg. 1830) for times.\n"
                + "\t____________________________________________________________\n");
    }

    /**
     * Prints a message to indicate successful task addition.
     *
     * @param tasks TaskList containing the Task.
     * @param task Task that is added.
     */
    public void showAddTask(TaskList tasks, Task task) {
        System.out.println("\t____________________________________________________________\n"
                + "\tGot it. I've added this task:\n"
                + "\t   " + task.toString() + "\n"
                + "\tNow you have " + tasks.size() + " tasks in the list.\n"
                + "\t____________________________________________________________\n");
    }

    /**
     * Prints a message to indicate successful task deletion.
     *
     * @param tasks TaskList that contained the Task.
     * @param task Task that has been deleted.
     */
    public void showDeleteTask(TaskList tasks, Task task) {
        System.out.println("\t____________________________________________________________\n"
                + "\tNoted. I've removed this task:\n"
                + "\t   " + task.toString() + "\n"
                + "\tNow you have " + tasks.size() + " tasks in the list.\n"
                + "\t____________________________________________________________\n");
    }

    /**
     * Prints a message to indicate successful task completion.
     *
     * @param tasks TaskList containing the Task to be completed.
     * @param index Index of the task in TaskList.
     */
    public void showDoneTask(TaskList tasks, int index) {
        System.out.println("\t____________________________________________________________\n"
                + "\tNice! I've marked this task as done:\n\t\t"
                + tasks.getTask(index - 1).toString() + "\n"
                + "\t____________________________________________________________\n");
    }

    /**
     * Prints the goodbye message.
     */
    public void showByeMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n");
    }

    /**
     * Prints the list of tasks matching specified input.
     *
     * @param tasks List of task that contains the matched input.
     */
    public void showMatchList(TaskList tasks) {
        System.out.println("\t____________________________________________________________\n"
                + "\tHere are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.getTask(i).toString());
        }
        System.out.println("\t____________________________________________________________\n");
    }

    /**
     * Prints a message indicating no match in TaskList.
     */
    public void showNoMatch() {
        System.out.println("\t____________________________________________________________\n"
                + "\tNo tasks matched your word.\n"
                + "\t____________________________________________________________\n");
    }
}
