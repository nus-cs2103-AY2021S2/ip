package duke;

import duke.tasks.Task;
import java.util.Scanner;
import java.util.List;

public class Ui {

    /**Scanner instance used by UI to read in user input */
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns A string which is the user input.
     *
     * @return user input.
     */
    public String read() {
        String action = sc.nextLine();
        return action;
    }

    /**
     * Prints the divider line.
     */
    public void printDivider() {
        String divider = "    ___________________________________________";
        System.out.println(divider);
    }

    /**
     * Prints the welcome message.
     */
    public void welcome() {
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
    }

    /**
     * Prints the goodbye message.
     */
    public void bye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    /**
     * Prints out a task.
     *
     * @param task  A task.
     */
    public void printTask(Task task) {
        System.out.println(task);
    }

    /**
     * Prints out the string input
     *
     * @param str A string
     */
    public void print(String str) {
        System.out.println(str);
    }

    /**
     * Prints the message when a task is added.
     */
    public void addPrint() {
        System.out.println("     Got it. I've added this task: ");
    }

    /**
     * Prints the error when attempting to load file message.
     */
    public void showLoadingError() {
        System.out.println("     Unable to load file. Creating new one");
    }

    /**
     * Prints out the number of tasks inside the TaskList
     *
     * @param list Tasklist
     */
    public void countTasks(TaskList list) {
        System.out.println("     Now you have " + list.getList().size() + " tasks in the list.");
    }

    /**
     * Prints the message when a task is removed.
     */
    public static void printRemoved() {
        System.out.println("     Noted. I've removed this task: ");
    }

    /**
     * Prints the message when a task is marked.
     */
    public static void printMarked() {
        System.out.println("     " + "Nice! I've marked this task as done:");
    }

    /**
     * Prints out the number of tasks inside the TaskList on list command.
     *
     * @param list The tasklist.
     */
    public void printStored(TaskList list) {
        List<Task> taskList = list.getList();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < list.getList().size(); i++) {
            Task task = taskList.get(i);
            System.out.println("      " + (i + 1) + "." + task.toString().trim());
        }
    }
}
