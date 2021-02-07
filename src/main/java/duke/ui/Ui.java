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
     * Prints the welcome message of Duke.
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
     * Prints a formatted message.
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
     * Prints the exit message of Duke.
     */
    public void showBye() {
        printFormatted("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all <code>Task</code> in the <code>TaskList</code>.
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
     * Prints a message when <code>Task</code> is added.
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
     * Prints a message when <code>Task</code> is removed.
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
     * Prints a message when <code>Task</code> is marked done.
     *
     * @param task the <code>Task</code> that is added
     */
    public void printMarked(Task task) {
        String msg = "Nice! I've marked this task as done:\n";
        msg += "  " + task;
        printFormatted(msg);
    }

    /**
     * Prints all the <code>Task</code> in <code>TaskList</code> with a message
     *
     * @param tasks TaskList to be printed
     */
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

    /**
     * Reads the user input and return as a String
     *
     * @return String representing user input
     */
    public String readLine() {
        return this.scanner.nextLine();
    }
}
