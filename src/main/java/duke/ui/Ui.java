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

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String nextUserInput() {
        return sc.nextLine().trim();
    }

    public void close() {
        sc.close();
    }

    public void printPartitionLine() {
        System.out.println(PARTITION_LINE);
    }

    public void printGreeting() {
        printPartitionLine();
        System.out.println(GREETING_MESSAGE);
        printPartitionLine();
    }

    public void printFarewell() {
        printPartitionLine();
        System.out.println(FAREWELL_MESSAGE);
        printPartitionLine();
    }

    public void printErrorMessage(String message) {
        printPartitionLine();
        System.out.println(ERROR_START + message);
        printPartitionLine();
    }

    public void printAddTaskReport(Task task, TaskList tasks) {
        printPartitionLine();
        System.out.println(TASK_ADDED_MESSAGE);
        printTask(task);
        printTaskCount(tasks);
        printPartitionLine();
    }

    public void printTaskCount(TaskList tasks) {
        System.out.println(INDENTATION + "Now you have " + tasks.getTaskCount() + " in the list.");
    }

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

    public void printDeleteTaskMessage(Task task, TaskList tasks) {
        printPartitionLine();
        System.out.println(TASK_REMOVED_MESSAGE);
        printTask(task);
        printTaskCount(tasks);
        printPartitionLine();
    }

    public void printTask(Task task) {
        System.out.println(INDENTATION + INDENTATION + task.toString());
    }

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
