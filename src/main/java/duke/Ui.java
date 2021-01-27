package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * Echos the command back to the user.
     * @return command (full line) entered by the user
     */
    public String readCommand() {
        String command = null;
        if (sc.hasNextLine()) {
            command = sc.nextLine();
        }
        return command;
    }

    /**
     * Prints an horizontal line upon request.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the welcome message upon the start of the application.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Prints a farewell message to the user.
     */
    public void showExit() {
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints an error message when there is no stored list of task.
     */
    public void showLoadingError() {
        showLine();
        System.out.println("No existing task list was found. A new task list will be created.");
        showLine();
    }

    /**
     * Prints a formatted list of task(s) to the user.
     * @param taskList current task list
     */
    public void showListMessage(TaskList taskList, boolean isFind) {
        System.out.printf("Here are the %s in your list:%n", isFind ? "matching tasks" : "tasks");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, taskList.get(i).toString());
        }
    }

    /**
     * Prints the information of the new task.
     * @param task the newly added task
     * @param listSize size of the current task list
     */
    public void showAddMessage(Task task, int listSize) {
        System.out.println("Got it. I've added this task:");
        formattedPrint(task.toString());
        showSummaryMessage(listSize);
    }

    /**
     * Prints the information of the completed task.
     * @param task the completed task
     */
    public void showDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        formattedPrint(task.toString());
    }

    public void showDoneMessage(TaskList taskList) {
        System.out.println("Nice! I've marked all your tasks as done:");
        printList(taskList);
    }

    /**
     * Prints the information of the deleted task.
     * @param task the deleted task
     * @param listSize size of the current task list
     */
    public void showDeleteMessage(Task task, int listSize) {
        System.out.println("Noted. I've removed this task:");
        formattedPrint(task.toString());
        showSummaryMessage(listSize);
    }

    /**
     * Prints the information of the deleted task.
     */
    public void showDeleteMessage(TaskList taskList) {
        System.out.println("Noted. I've removed all your tasks.");
        printList(taskList);
        showSummaryMessage(0);
    }

    /**
     * Prints a summary information of the task list.
     * @param listSize size of the current task list
     */
    private void showSummaryMessage(int listSize) {
        System.out.printf("Now you have %d %s in the list.%n", listSize, listSize >= 2 ? "tasks" : "task");
    }

    /**
     * Prints the error message.
     * @param message message to be printed
     */
    public void showError(String message) {
        System.out.printf("☹️ OOPS!!! %s%n", message);
    }

    /**
     * Prepend the message with indent, then print.
     * @param message message to be printed
     */
    private void formattedPrint(String message) {
        final String PRINT_FORMAT = "\t%s%n";
        System.out.printf(PRINT_FORMAT, message);
    }

    private void printList(TaskList taskList) {
        for (Task task : taskList.getTaskList()) {
            formattedPrint(task.toString());
        }
    }
}
