package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Handles Ui related functions such as input and output.
 */
public class Ui {
    private static final String BOT_NAME = "Chip the Squirrel";
    private static final String LINE_BREAK = "------------------------------------------------------------";
    private static final String INDENT = "    ";
    private final Scanner sc;

    /**
     * Create new instance of Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints input strings with indentation and top and bottom lines.
     * Each String is printed on a new line.
     *
     * @param strings input strings.
     */
    private static void printWithIndentation(String... strings) {
        System.out.println(INDENT + LINE_BREAK);

        for (String s : strings) {
            System.out.println(INDENT + s);
        }

        System.out.println(INDENT + LINE_BREAK);
    }

    /**
     * Checks if there is any more user input to process.
     *
     * @return true if there is still user input, false otherwise.
     */
    public boolean hasMoreTokens() {
        return sc.hasNext();
    }

    /**
     * Gets next line of user input.
     *
     * @return user input
     */
    public String getUserCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Displays welcome message.
     */
    public void showWelcomeMessage() {
        printWithIndentation("Hello! I'm " + BOT_NAME + "!", "What can I do for you today?");
    }

    /**
     * Displays goodbye message.
     */
    public void showGoodbyeMessage() {
        printWithIndentation("Bye! Hope to see you again soon!");
    }

    /**
     * Displays error message.
     *
     * @param errorMessage message to be displayed.
     */
    public void showErrorMessage(String errorMessage) {
        printWithIndentation(errorMessage);
    }

    /**
     * Displays list of current tasks.
     *
     * @param taskList list of current tasks.
     */
    public void showTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            printWithIndentation("You have not added any tasks.");
        } else {
            String[] tasksArr = new String[taskList.size()];

            for (int i = 0; i < taskList.size(); i++) {
                tasksArr[i] = (i + 1) + "." + taskList.get(i).toString();
            }

            printWithIndentation(tasksArr);
        }
    }

    /**
     * Displays list of matching tasks after search.
     * @param tasks List of tasks.
     */
    public void showFilteredTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            printWithIndentation("No matching tasks were found.");
        } else {
            String[] outputArr = new String[tasks.size() + 1];
            outputArr[0] = "Here are the matching tasks in your list:";

            for (int i = 0; i < tasks.size(); i++) {
                outputArr[i + 1] = (i + 1) + "." + tasks.get(i).toString();
            }

            printWithIndentation(outputArr);
        }
    }

    /**
     * Displays message after user successfully deletes a task.
     *
     * @param numTasks number of tasks left.
     * @param task     task that was deleted.
     */
    public void showSuccessfulDeleteMessage(int numTasks, Task task) {
        String formattedTasksCount = numTasks > 1 ? String.format("%d tasks", numTasks) : "1 task";

        printWithIndentation("Got it! I've removed this task:",
            "  " + task.toString(),
            "Now you have " + formattedTasksCount + " in the list.");
    }

    /**
     * Displays message after user successfully marks a task as done.
     *
     * @param task task that was marked as done.
     */
    public void showSuccessfulDoneMessage(Task task) {
        printWithIndentation("Good Job! I've marked this task as done!", task.toString());
    }

    /**
     * Displays message after user successfully adds a task.
     *
     * @param numTasks number of tasks left.
     * @param task     task that was added.
     */
    public void showAddTaskMessage(int numTasks, Task task) {
        String formattedTasksCount = numTasks > 1 ? String.format("%d tasks", numTasks) : "1 task";

        printWithIndentation("Got it! I've added this task:",
            "  " + task.toString(),
            "Now you have " + formattedTasksCount + " in the list.");
    }
}
