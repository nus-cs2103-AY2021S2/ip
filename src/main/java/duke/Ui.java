package duke;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

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
     *
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
     * Prints a formatted {@code TaskList} to the user.
     *
     * @param taskList {@code TaskList} to be printed
     * @param isFind an indicator used to differentiate list and find operation
     */
    public void showListMessage(TaskList taskList, boolean isFind) {
        System.out.printf("Here are the %s in your list:%n", isFind ? "matching tasks" : "tasks");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, taskList.get(i).toString());
        }
    }

    /**
     * Prints the information of the new {@code Task}.
     *
     * @param task the newly added {@code Task}
     * @param listSize size of the current {@code TaskList}
     */
    public void showAddMessage(Task task, int listSize) {
        System.out.println("Got it. I've added this task:");
        formattedPrint(task.toString());
        showSummaryMessage(listSize);
    }

    /**
     * Prints the information of the completed {@code Task}.
     *
     * @param task the completed {@code Task}
     */
    public void showDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        formattedPrint(task.toString());
    }

    /**
     * Prints the information of the {@code TaskList} of completed task.
     *
     * @param taskList the {@code TaskList} to be printed
     */
    public void showDoneMessage(TaskList taskList) {
        System.out.println("Nice! I've marked all your tasks as done:");
        printList(taskList);
    }

    /**
     * Prints the information of the deleted {@code Task}.
     *
     * @param task the deleted {@code Task}
     * @param listSize size of the current {@code TaskList}
     */
    public void showDeleteMessage(Task task, int listSize) {
        System.out.println("Noted. I've removed this task:");
        formattedPrint(task.toString());
        showSummaryMessage(listSize);
    }

    /**
     * Prints the information of the deleted {@code TaskList}.
     *
     * @param taskList the {@code TaskList} to be removed
     */
    public void showDeleteMessage(TaskList taskList) {
        System.out.println("Noted. I've removed all your tasks.");
        printList(taskList);
        showSummaryMessage(0);
    }

    /**
     * Prints a summary information of the {@code TaskList}.
     *
     * @param listSize size of the current {@code TaskList}
     */
    private void showSummaryMessage(int listSize) {
        System.out.printf("Now you have %d %s in the list.%n", listSize, listSize >= 2 ? "tasks" : "task");
    }

    /**
     * Prints the error message.
     *
     * @param message message to be printed
     */
    public void showError(String message) {
        System.out.printf("☹️ OOPS!!! %s%n", message);
    }

    /**
     * Prepend the message with indent, then print.
     *
     * @param message string to be printed
     */
    private void formattedPrint(String message) {
        String printFormat = "\t%s%n";
        System.out.printf(printFormat, message);
    }

    /**
     * Prints all the tasks stored in {@code TaskList}
     *
     * @param taskList the {@code TaskList} to be printed
     */
    private void printList(TaskList taskList) {
        for (Task task : taskList.getTaskList()) {
            formattedPrint(task.toString());
        }
    }
}
