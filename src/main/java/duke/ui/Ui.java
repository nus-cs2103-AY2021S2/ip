package duke.ui;

import java.util.LinkedList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A class for handling input and output for Duke.
 */
public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the exit message of Duke.
     */
    public String getByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints all <code>Task</code> in the <code>TaskList</code>.
     *
     * @param tasks a <code>TaskList</code> consisting of tasks
     */
    public String getListMessage(TaskList tasks) {
        LinkedList<Task> lst = tasks.getList();
        String msg = "Here are the tasks in your list:\n";
        for (int i = 1; i <= lst.size(); i++) {
            msg += i + ". " + lst.get(i - 1);
            if (i < lst.size()) {
                msg += "\n";
            }
        }
        return msg;
    }

    /**
     * Prints a message when <code>Task</code> is added.
     *
     * @param tasks the <code>TaskList</code> containing all the tasks
     * @param task the <code>Task</code> that is added
     */
    public String getAddMessage(TaskList tasks, Task task) {
        String msg = "Got it. I've added this task:\n"
                + "  " + task
                + "\nNow you have " + tasks.getList().size() + " tasks in the list.";
        return msg;
    }

    /**
     * Prints a message when <code>Task</code> is removed.
     *
     * @param tasks the <code>TaskList</code> containing all the tasks
     * @param task the <code>Task</code> that is added
     */
    public String getRemoveMessage(TaskList tasks, Task task) {
        String msg = "Got it. I've removed this task:\n"
                + "  " + task
                + "\nNow you have " + tasks.getList().size() + " tasks in the list.";
        return msg;
    }

    /**
     * Prints a message when <code>Task</code> is marked done.
     *
     * @param task the <code>Task</code> that is added
     */
    public String getMarkMessage(Task task) {
        String msg = "Nice! I've marked this task as done:\n";
        msg += "  " + task;
        return msg;
    }

    /**
     * Prints all the <code>Task</code> in <code>TaskList</code> with a message
     *
     * @param tasks TaskList to be printed
     */
    public String getFoundMessage(TaskList tasks) {
        LinkedList<Task> lst = tasks.getList();
        String msg = "Here are the matching tasks in your list:\n";
        for (int i = 1; i <= lst.size(); i++) {
            msg += i + ". " + lst.get(i - 1);
            if (i < lst.size()) {
                msg += "\n";
            }
        }
        return msg;
    }
}
