package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A class represents a UI.
 */
public class Ui {
    private static final String PARTING_LINE = "____________________________________________________________";

    /**
     * Returns an input from user.
     * @return A string of input from user.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints the greeting message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(PARTING_LINE);
        System.out.println("Sup. I am Duke.");
        System.out.println("How can I help you?");
        System.out.println(PARTING_LINE);
    }

    /**
     * Prints a separating line.
     */
    public void printLine() {
        System.out.println(PARTING_LINE);
    }

    /**
     * Prints "See you" to the user.
     */
    public void sayGoodBye() {
        System.out.println(" See you.");
    }

    public void printTaskAdded(Task task, TaskList tasks) {
        System.out.println(" Added: ");
        System.out.println("  " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks.");
    }

    /**
     * Prints all the tasks in the TaskList.
     * @param tasks The TaskList to be printed.
     */
    public void listTasks(TaskList tasks) {
        System.out.println(" Here are the tasks: ");
        for (int i = 0; i < tasks.size(); i++) {
            Task tempTask = tasks.get(i);
            System.out.println(" " + (i + 1) + "." + tempTask);
        }
    }

    /**
     * Prints all the tasks that matches.
     * @param tasks The matching TaskList to be printed.
     */
    public void listMatchingTasks(TaskList tasks) {
        System.out.println(" Here are the matching tasks: ");
        for (int i = 0; i < tasks.size(); i++) {
            Task tempTask = tasks.get(i);
            System.out.println(" " + (i + 1) + "." + tempTask);
        }
    }

    public String addTaskResponse(Task task, TaskList tasks) {
        return "Added:\n" + "  " + task.toString() + "\n" + " Now you have " + tasks.size() + " tasks.";
    }

    public String deleteTaskResponse(Task task, TaskList tasks) {
        return "Removed:\n" + "  " + task.toString() + "\n" + " Now you have " + tasks.size() + " tasks.";
    }

    public String completeTaskResponse(Task task) {
        return "Marked:\n" + "  " + task.toString();
    }

    public String exitResponse() {
        return "See you.";
    }

    public String listTasksResponse(TaskList tasks) {
        String response = " Here are the tasks:\n";
        for (int i = 0; i < tasks.size(); i++) {
            String thisTask = (i + 1) + ". " + tasks.get(i).toString() + "\n";
            response += thisTask;
        }
        return response;
    }

    public String listMatchingTasksResponse(TaskList matchingTasks) {
        String response = " Here are the tasks:\n";
        for (int i = 0; i < matchingTasks.size(); i++) {
            String thisTask = (i + 1) + ". " + matchingTasks.get(i).toString() + "\n";
            response += thisTask;
        }
        return response;
    }

    public String showErrorMessage(String message) {
        System.out.println(message);
        return message;
    }
}
