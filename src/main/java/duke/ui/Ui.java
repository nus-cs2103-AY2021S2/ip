package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    private static final String INDENTATION = "    ";
    private static final String NEW_LINE = "\n    ";
    private static final String PARTITION_LINE = INDENTATION + "---------------------------";
    private static final String LOGO = "    __  _____ _  ___   ___   _ ___\n" +
            "    \\ \\/ /_ _| \\| \\ \\ / / | | | __|\n" +
            "     >  < | || .` |\\ V /| |_| | _|\n" +
            "    /_/\\_\\___|_|\\_| |_|  \\___/|___|\n";
    private static final String GREETING_MESSAGE =
            INDENTATION + "Hi there! Welcome to\n" + LOGO + NEW_LINE + "What can I do for you today?";
    private static final String FAREWELL_MESSAGE = INDENTATION + "Goodbye. Have a nice day!!";
    private static final String ERROR_START = INDENTATION + "OOPS!!! ";
    private static final String TASK_ADDED_MESSAGE = INDENTATION + "Got it. I've added this task:";
    private static final String TASK_COMPLETED_MESSAGE = INDENTATION + "Congratulations! You have completed this task:";
    private static final String TASK_ALR_COMPLETED_MESSAGE = INDENTATION + "You have already completed this task:";
    private static final String TASK_REMOVED_MESSAGE = INDENTATION + "Noted. This task has been removed:";
    private static final String EMPTY_LIST_MESSAGE = INDENTATION + "It seems like there is nothing in your list.";
    private static final String PRINT_LIST_MESSAGE = INDENTATION + "Here are the tasks in your list:";

    /**
     * Initializes the required object for an Ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads a line of input entered by user.
     *
     * @return String containing user input
     */
    public String nextUserInput() {
        return sc.nextLine().trim();
    }

    /**
     * Terminates the functioning Ui Object.
     */
    public void close() {
        sc.close();
    }

    /**
     * Prints a partition line.
     */
    public void printPartitionLine() {
        System.out.println(PARTITION_LINE);
    }

    /**
     * Prints the greeting message.
     */
    public void printGreeting() {
        printPartitionLine();
        System.out.println(GREETING_MESSAGE);
        printPartitionLine();
    }

    /**
     * Prints the farewell message.
     */
    public void printFarewell() {
        printPartitionLine();
        System.out.println(FAREWELL_MESSAGE);
        printPartitionLine();
    }

    /**
     * Prints a error message after formatting.
     *
     * @param message Content of the error message.
     */
    public void printErrorMessage(String message) {
        printPartitionLine();
        System.out.println(ERROR_START + message);
        printPartitionLine();
    }

    /**
     * Prints a feedback message after user adds a task.
     *
     * @param task  Task added by user.
     * @param tasks List of tasks the task was added to.
     */
    public void printAddTaskReport(Task task, TaskList tasks) {
        printPartitionLine();
        System.out.println(TASK_ADDED_MESSAGE);
        printTask(task);
        printTaskCount(tasks);
        printPartitionLine();
    }

    /**
     * Prints a message containing the total number of tasks.
     *
     * @param tasks List of all the tasks.
     */
    public void printTaskCount(TaskList tasks) {
        System.out.println(INDENTATION + "Now you have " + tasks.getTaskCount() + " in the list.");
    }

    /**
     * Prints a feedback message after user marks a task as done.
     *
     * @param task    Task marked as done.
     * @param wasDone If the task was already marked as done.
     */
    public void printMarkTaskAsDoneMessage(Task task, boolean wasDone) {
        printPartitionLine();
        if (wasDone) {
            System.out.println(TASK_ALR_COMPLETED_MESSAGE);
        } else {
            System.out.println(TASK_COMPLETED_MESSAGE);
        }
        printTask(task);
        printPartitionLine();
    }

    /**
     * Prints a feedback message after user deletes a task.
     *
     * @param task  Task deleted by user.
     * @param tasks List of tasks the task was deleted from.
     */
    public void printDeleteTaskMessage(Task task, TaskList tasks) {
        printPartitionLine();
        System.out.println(TASK_REMOVED_MESSAGE);
        printTask(task);
        printTaskCount(tasks);
        printPartitionLine();
    }

    /**
     * Print a task after formatting.
     *
     * @param task Task to be displayed.
     */
    public void printTask(Task task) {
        System.out.println(INDENTATION + INDENTATION + task.toString());
    }

    /**
     * Prints all the tasks after formatting.
     *
     * @param tasks List of all the tasks.
     */
    public void printAllTasks(TaskList tasks) {
        printPartitionLine();
        if (tasks.isEmpty()) {
            System.out.println(EMPTY_LIST_MESSAGE);
        } else {
            System.out.println(PRINT_LIST_MESSAGE);
            for (int i = 1; i <= tasks.getTaskCount(); ++i) {
                System.out.println("    " + i + "." + tasks.getTask(i - 1).toString());
            }
        }
        printPartitionLine();
    }

}
