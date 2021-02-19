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

    /**
     * Prints the task added.
     * @param task The task added.
     * @param tasks The TaskList to be updated.
     */
    public void printTaskAdded(Task task, TaskList tasks) {
        System.out.println(" Added: ");
        System.out.println("  " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks.");
    }

    /**
     * Prints the task removed.
     * @param task The task removed.
     * @param tasks The TaskList to be updated.
     */
    public void printTaskRemoved(Task task, TaskList tasks) {
        System.out.println(" Following task is removed:");
        System.out.println("  " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks.");
    }

    /**
     * Prints the task completed.
     * @param task The task completed.
     */
    public void printTaskCompleted(Task task) {
        System.out.println(" Marked. How cool is that?");
        System.out.println("  " + task);
    }

    /**
     * Prints the task whose priority has been updated.
     * @param task The task whose priority has been updated.
     */
    public void printTaskUpdated(Task task) {
        System.out.println(" Updated the priority of following task:");
        System.out.println("  " + task);
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

    /**
     * Returns the response of adding a task.
     * @param task The task added.
     * @param tasks The TaskList to be updated.
     * @return The response of adding a task.
     */
    public String addTaskResponse(Task task, TaskList tasks) {
        return "Added:\n" + "  " + task.toString() + "\n" + " Now you have " + tasks.size() + " tasks.";
    }

    /**
     * Returns the response of deleting a task.
     * @param task The task to be removed
     * @param tasks The TaskList to be updated.
     * @return The response of deleting a task.
     */
    public String deleteTaskResponse(Task task, TaskList tasks) {
        return "Removed:\n" + "  " + task.toString() + "\n" + " Now you have " + tasks.size() + " tasks.";
    }

    /**
     * Returns the response of completing a task.
     * @param task The task completed.
     * @return The response of completing a task.
     */
    public String completeTaskResponse(Task task) {
        return "Marked:\n" + "  " + task.toString();
    }

    public String updateTaskResponse(Task task) {
        return "Priority has been updated:\n" + "  " + task.toString();
    }

    /**
     * Returns the response of exit command.
     * @return The message of farewell.
     */
    public String exitResponse() {
        return "Goodbye until we meet again.";
    }

    /**
     * Returns the tasks as a string.
     * @param tasks The tasks to be returned.
     * @return The tasks as a string.
     */
    public String listTasksResponse(TaskList tasks) {
        StringBuilder response = new StringBuilder(" Here are the tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            String thisTask = (i + 1) + ". " + tasks.get(i).toString() + "\n";
            response.append(thisTask);
        }
        return response.toString();
    }

    /**
     * Returns all the matching tasks as a string.
     * @param matchingTasks The matching tasks to be returned.
     * @return All the matching tasks as a string.
     */
    public String listMatchingTasksResponse(TaskList matchingTasks) {
        StringBuilder response = new StringBuilder(" Here are the matching tasks:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            String thisTask = (i + 1) + ". " + matchingTasks.get(i).toString() + "\n";
            response.append(thisTask);
        }
        return response.toString();
    }

    /**
     * Returns the error message.
     * @param message The error message.
     * @return The error message.
     */
    public String showErrorMessage(String message) {
        System.out.println(message);
        return message;
    }
}
