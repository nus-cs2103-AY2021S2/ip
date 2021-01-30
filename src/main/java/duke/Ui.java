package duke;

import java.util.List;
import java.util.Scanner;

import duke.tasks.Task;


/**
 * UI class that is responsible for reading user input and printing
 */
public class Ui {
    /**
     * Constructor to initialize the UI Class
     */
    public Ui() {
    }

    /**
     * Returns A string which is the user input.
     *
     * @return user input.
     */
    public String read() {
        Scanner sc = new Scanner(System.in);
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
    public void printWelcome() {
        String introduction = "     Hello! I'm Duke\n" + "     What can I do for you?";
        System.out.println(introduction);
    }

    /**
     * Prints the goodbye message.
     */
    public void printBye() {
        String farewell = "     Bye. Hope to see you again soon!";
        System.out.println(farewell);
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
        String printAddTask = "     Got it. I've added this task: ";
        System.out.println(printAddTask);
    }

    /**
     * Prints the error when attempting to load file message.
     */
    public void showLoadingError() {

        String unableToLoad = "     Unable to load file. Creating new one";
        System.out.println(unableToLoad);
    }

    /**
     * Prints out the number of tasks inside the TaskList
     *
     * @param list Tasklist
     */
    public void countTasks(TaskList list) {

        String countTasksMessage = "     Now you have " + list.getList().size() + " tasks in the list.";
        System.out.println(countTasksMessage);
    }

    /**
     * Prints the message when a task is removed.
     */
    public static void printRemoved() {

        String removeTasksMessage = "     Noted. I've removed this task: ";
        System.out.println(removeTasksMessage);
    }

    /**
     * Prints the message when a task is marked.
     */
    public static void printMarked() {

        String taskMarkedMessage = "     Nice! I've marked this task as done:";
        System.out.println(taskMarkedMessage);
    }
    /**
     * @param list the Task list to be printed
     * Prints out the current task list
     */
    public void printList(List<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println("      " + (i + 1) + "." + task.toString().trim());
        }
    }
    /**
     * Prints out the number of tasks inside the TaskList on list command.
     *
     * @param list The tasklist.
     */
    public void printStored(TaskList list) {
        List<Task> taskList = list.getList();

        String taskListMessage = "     Here are the tasks in your list:";
        System.out.println(taskListMessage);

        this.printList(taskList);
    }
    /**
     * Prints out the matching message when user is finding for a keyword in the list
     */
    public void printMatching() {
        String printMatchingMessage = "     Here are the matching tasks in your list: ";
        System.out.println(printMatchingMessage);
    }
}
