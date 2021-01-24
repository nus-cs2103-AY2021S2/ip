package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    private static final String INDENTATION = "    ";
    private static final String NO_FOUND_TASK_MESSAGE =
            INDENTATION + "It seems like there is no task matching your keyword:";
    private static final String PRINT_FOUND_TASKS_MESSAGE = INDENTATION + "Here are the matching tasks in your list:";

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
        System.out.println("    ---------------------------");
    }

    public void printGreeting() {
        printPartitionLine();
        String logo = "    __  _____ _  ___   ___   _ ___\n" +
                "    \\ \\/ /_ _| \\| \\ \\ / / | | | __|\n" +
                "     >  < | || .` |\\ V /| |_| | _|\n" +
                "    /_/\\_\\___|_|\\_| |_|  \\___/|___|\n";
        System.out.println("    Hi there! Welcome to\n" + logo);
        System.out.println("    What can I do for you today?");
        printPartitionLine();
    }

    public void printFarewell() {
        printPartitionLine();
        System.out.println("    Goodbye. Have a nice day!!");
        printPartitionLine();
    }

    public void printErrorMessage(String message) {
        printPartitionLine();
        System.out.println("    OOPS!!! " + message);
        printPartitionLine();
    }

    public void printAddTaskReport(Task task, TaskList tasks) {
        printPartitionLine();
        System.out.println("    Got it. I've added this task");
        System.out.println("        " + task.toString());
        printTaskCount(tasks);
        printPartitionLine();
    }

    public void printTaskCount(TaskList tasks) {
        System.out.println("    Now you have " + tasks.getTaskCount() + " in the list.");
    }

    public void printMarkTaskAsDoneMessage(Task task, boolean wasDone) {
        printPartitionLine();
        if (wasDone) {
            System.out.println("    You have already completed this task:");
        } else {
            System.out.println("    Congratulations! You have completed this task:");
        }
        System.out.println("        " + task.toString());
        printPartitionLine();
    }

    public void printDeleteTaskMessage(Task task, TaskList tasks) {
        printPartitionLine();
        System.out.println("    Noted. This task has been removed:");
        System.out.println("        " + task.toString());
        printTaskCount(tasks);
        printPartitionLine();
    }

    public void printAllTasks(TaskList tasks) {
        printPartitionLine();
        if (tasks.isEmpty()) {
            System.out.println("    It seems like there is nothing in your list.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            printTasks(tasks);
        }
        printPartitionLine();
    }

    /**
     * Prints all the tasks found by a keyword after formatting.
     *
     * @param tasks List of found tasks.
     */
    public void printAllFoundTasks(TaskList tasks) {
        printPartitionLine();
        if (tasks.isEmpty()) {
            System.out.println(NO_FOUND_TASK_MESSAGE);
        } else {
            System.out.println(PRINT_FOUND_TASKS_MESSAGE);
            printTasks(tasks);
        }
        printPartitionLine();
    }

    /**
     * Prints all the tasks in the given TaskList.
     *
     * @param tasks TaskList to be printed.
     */
    public void printTasks(TaskList tasks) {
        for (int i = 1; i <= tasks.getTaskCount(); ++i) {
            System.out.println("    " + i + "." + tasks.getTask(i - 1).toString());
        }
    }

}
