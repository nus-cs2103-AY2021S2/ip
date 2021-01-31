package duke.ui;

import java.util.Scanner;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Responsible for handling output messages.
 */
public class Ui {
    public Ui() {}

    /**
     * Returns the Duke greeting message.
     *
     * @return Duke greeting message.
     */
    public String getGreeting() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Returns the user input as a string.
     *
     * @return user input string.
     */
    public String readCommand() {
        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }

    /**
     * Returns the string listing tasks in TaskList, numbered in increasing order.
     *
     * @param tasks TaskList to be printed.
     * @return String listing tasks in TaskList.
     */
    public String getListOfTasks(TaskList tasks) {
        String taskListString = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            taskListString += (i + 1) + "." + tasks.getTask(i).toString() + "\n";
        }
        return taskListString;
    }

    /**
     * Returns a message to indicate TaskList is empty.
     *
     * @return String indicating an empty TaskList.
     */
    public String getEmptyListString() {
        return "There are no items in your list.";
    }

    /**
     * Returns a message to indicate error when editing save file.
     *
     * @return String indicating error when editing save file.
     */
    public String getIoErrorString() {
        return "Error happened while trying to edit save file.";
    }

    /**
     * Returns a message to indicate invalid date input.
     *
     * @return String to indicating invalid date input.
     */
    public String getOutOfBoundsErrorString() {
        return "Please enter the date (DD/MM/YYYY) with optional"
                + "time (in 24 hours format) after \"/by\" for Deadline Tasks "
                + "or date with optional start and end time after \"/at\" "
                + "for Event Tasks.";
    }

    /**
     * Returns a message to indicate invalid date input format.
     *
     * @return String indicating invalid date input format.
     */
    public String getDateTimeParseErrorString() {
        return "Please enter in DD/MM/YYYY format (eg. 02/04/2000) for dates "
                + "and in 24 hour format (eg. 1830) for times.";
    }

    /**
     * Returns a message to indicate successful task addition.
     *
     * @param tasks TaskList containing the Task.
     * @param task Task that is added.
     * @return String to indicate successful task addition.
     */
    public String getAddTaskString(TaskList tasks, Task task) {
        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Returns a message to indicate successful task deletion.
     *
     * @param tasks TaskList that contained the Task.
     * @param task Task that has been deleted.
     * @return String to indicate successful task deletion.
     */
    public String getDeleteTaskString(TaskList tasks, Task task) {
        return "Noted. I've removed this task:\n"
                + task.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Returns a message to indicate successful task completion.
     *
     * @param tasks TaskList containing the Task to be completed.
     * @param index Index of the task in TaskList.
     * @return String to indicate successful task completion.
     */
    public String getDoneTaskString(TaskList tasks, int index) {
        return "Nice! I've marked this task as done:\n"
                + tasks.getTask(index - 1).toString();
    }

    /**
     * Returns the goodbye message.
     *
     * @return Goodbye message string.
     */
    public String getByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a string listing the tasks matching specified input.
     *
     * @param tasks List of task that contains the matched input.
     * @return String listing the tasks matching specified input.
     */
    public String getMatchList(TaskList tasks) {
        String matchString = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            matchString += (i + 1) + "." + tasks.getTask(i).toString() + "\n";
        }
        return matchString;
    }

    /**
     * Returns a message indicating no match in TaskList.
     *
     * @return String to indicate no match in TaskList.
     */
    public String getNoMatchString() {
        return "No tasks matched your word.";
    }

    /**
     * Prints a message indicating file does not exists.
     */
    public void showFileNotFound() {
        System.out.println("Error: File does not exists.");
    }

    /**
     * Prints a message indicating invalid content format in save file.
     */
    public void showInvalidSaveFileFormat() {
        System.out.println("Error: Invalid content format in save file");
    }

    /**
     * Prints a message indicating invalid date time format in save file.
     */
    public void showInvalidSaveFileDateTimeFormat() {
        System.out.println("Error: Invalid date time format in save file or no date time stated");
    }

    /**
     * Prints a message indicating error while creating save file.
     */
    public void showFileCreationError() {
        System.out.println("Error happened while trying to create save file");
    }
}
