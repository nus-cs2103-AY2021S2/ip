package duke.ui;

import duke.task.TaskList;
import duke.task.Task;

import java.util.LinkedList;

/**
 * A class for outputting messages to user.
 */
public class Ui {

    public Ui() {

    }

    /**
     * Print the welcome message of Duke.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printFormatted("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Print a formatted message.
     * This method take in a message and print the formatted message
     * instantly.
     * @param msg a string to print
     */
    public void printFormatted(String msg) {
        String appendMsg = "____________________________________________________________\n"
                + msg
                + "\n____________________________________________________________";
        System.out.println(appendMsg);
    }

    /**
     * Print the exit message of Duke.
     */
    public void showBye() {
        printFormatted("Bye. Hope to see you again soon!");
    }

    /**
     * Print all <code>Task</code> in the <code>TaskList</code>.
     *
     * @param tasks a <code>TaskList</code> consisting of tasks
     */
    public void printList(TaskList tasks) {
        LinkedList<Task> lst = tasks.getList();
        String msg = "Here are the tasks in your list:\n";
        for (int i = 1; i <= lst.size(); i++) {
            msg += i + ". " + lst.get(i - 1);
            if (i < lst.size()) {
                msg += "\n";
            }
        }
        printFormatted(msg);
    }

    /**
     * Print a message when <code>Task</code> is added.
     *
     * @param tasks the <code>TaskList</code> containing all the tasks
     * @param task the <code>Task</code> that is added
     */
    public void printAdded(TaskList tasks, Task task) {
        String msg = "Got it. I've added this task:\n"
                + "  " + task
                + "\nNow you have " + tasks.getList().size() + " tasks in the list.";
        printFormatted(msg);
    }

    /**
     * Print a message when <code>Task</code> is removed.
     *
     * @param tasks the <code>TaskList</code> containing all the tasks
     * @param task the <code>Task</code> that is added
     */
    public void printRemoved(TaskList tasks, Task task) {
        String msg = "Got it. I've removed this task:\n"
                + "  " + task
                + "\nNow you have " + tasks.getList().size() + " tasks in the list.";
        printFormatted(msg);
    }

    /**
     * Print a message when <code>Task</code> is marked done.
     *
     * @param task the <code>Task</code> that is added
     */
    public void printMarked(Task task) {
        String msg = "Nice! I've marked this task as done:\n";
        msg += "  " + task;
        printFormatted(msg);
    }

    public void printFound(TaskList tasks) {
        LinkedList<Task> lst = tasks.getList();
        String msg = "Here are the matching tasks in your list:\n";
        for (int i = 1; i <= lst.size(); i++) {
            msg += i + ". " + lst.get(i - 1);
            if (i < lst.size()) {
                msg += "\n";
            }
        }
        printFormatted(msg);
    }
}
