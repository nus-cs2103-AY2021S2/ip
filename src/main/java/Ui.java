package main.java;

import main.java.entity.Task;
import java.util.List;
import java.util.Scanner;

/**
 * User Interface Management class that helps facilitate
 * interaction with user
 * particularly for user input passing
 * and display query result to user
 */
public class Ui {
    private Scanner sc;
    static final String GREETINGS = "Dear user, welcome to the world of duke!";
    static final String PREFIX = "    ";
    static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static final String DISPLAY_EMPTY_LIST = PREFIX + "Currently there is nothing on your list.";
    static final String DISPLAY_ADDED = PREFIX + "Got it. I've added this task:";
    static final String DISPLAY_DONE = PREFIX + "Nice! I've mark this task as done:";
    static final String DISPLAY_RENAME = PREFIX + "Noted, I've removed this task:";

    /**
     * Creates a Ui instance
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Show greetings display
     */
    public void greeting() {
        System.out.println("This is \n" + LOGO);
        System.out.println(GREETINGS);
    }

    /**
     * Show exit display
     */
    public void displayExit() {
        System.out.println(PREFIX + "Bye, until next time!");
    }

    /**
     * Show a horizontal line
     */
    public void displayLine() {
        System.out.println("________________________");
    }

    /**
     * Read a line of user input
     * @return a string of the line
     */
    public String readCommand() {
        return sc.nextLine();
    }


    public void displayWrongCommand(String message) {
        System.out.println(PREFIX + message);
        System.out.println(PREFIX + "Sorry, but we could not parse your input");
        System.out.println(PREFIX + "Please recheck its validity...");
    }

    public void displayOutOfRange(int index) {
        System.out.println(PREFIX + "There is no such index " + index + " in the task list.");
    }

    public void displayWrongCommand() {
        System.out.println(PREFIX + "Sorry, but we could not parse your input");
        System.out.println(PREFIX + "Please recheck its validity...");
    }

    /**
     * Display Error with particular error message
     * @param message particular error message
     */
    public void displayError(String message) {
        System.out.println("Duke is sorry about it, but we have encountered an error...");
        System.out.println(message);
    }

    /**
     * Display a task
     * @param task task to be displayed
     */
    public void displayTask(Task task) {
        System.out.println(PREFIX + task);
    }

    /**
     * Display info regarding current task list size
     * @param size list size
     */
    public void displayListSize(int size) {
        System.out.println(PREFIX + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Display when there is nothing in task list
     */
    public void displayEmptyList() {
        System.out.println(DISPLAY_EMPTY_LIST);
    }

    /**
     * Display all tasks
     * @param list list of tasks
     */
    public void displayAllTasks(List<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(PREFIX + (i + 1) + ". " + list.get(i));
        }
    }

    /**
     * Display after adding a task
     * @param size task list size after adding the task
     * @param task task added
     */
    public void displayAfterAdd(int size, Task task) {
        System.out.println(DISPLAY_ADDED);
        displayTask(task);
        displayListSize(size);
    }

    /**
     * Display after done a task
     * @param task task done
     */
    public void displayAfterDone(Task task) {
        System.out.println(DISPLAY_DONE);
        displayTask(task);
    }

    /**
     * Display after delete a task
     * @param size task list size after deleting the task
     * @param task task deleted
     */
    public void displayAfterDelete(int size, Task task) {
        System.out.println(DISPLAY_RENAME);
        System.out.println(PREFIX + task);
        displayListSize(size);
    }

    /**
     * Display search result based on given result task list
     * @param result task list found
     */
    public void displaySearchResult(List<Task> result) {
        if (result.isEmpty()) {
            System.out.println(prefix + "Sorry, there is no task found.");
        } else {
            System.out.println(prefix + "We found " + result.size() + " results:");
            for (Task task: result) {
                System.out.println(prefix + task);
            }
        }
    }
}
