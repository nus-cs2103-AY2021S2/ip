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
    static final String greetings = "Dear user, welcome to the world of duke!";
    static final String prefix = "    ";
    static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static final String displayEmptyList = prefix + "Currently there is nothing on your list.";
    static final String added = prefix + "Got it. I've added this task:";
    static final String markAsDone = prefix + "Nice! I've mark this task as done:";
    static final String removeTask = prefix + "Noted, I've removed this task:";

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
        System.out.println("This is \n" + logo);
        System.out.println(greetings);
    }

    /**
     * Show exit display
     */
    public void displayExit() {
        System.out.println(prefix + "Bye, until next time!");
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

    /**
     * Show duke wrong command error
     */
    public void displayWrongCommand() {
        System.out.println(prefix + "Sorry, but we could not parse your input");
        System.out.println(prefix + "Please recheck its validity...");
    }

    /**
     * Show a particular error message along with duke wrong command error
     * @param message the particular error message
     */
    public void displayWrongCommand(String message) {
        System.out.println(prefix + message);
        displayWrongCommand();
    }

    /**
     * Show when user query index is out of range
     * @param index user's input invalid index
     */
    public void displayOutOfRange(int index) {
        System.out.println(prefix + "There is no such index " + index + " in the task list.");
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
        System.out.println(prefix + task);
    }

    /**
     * Display info regarding current task list size
     * @param size list size
     */
    public void displayListSize(int size) {
        System.out.println(prefix + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Display when there is nothing in task list
     */
    public void displayEmptyList() {
        System.out.println(displayEmptyList);
    }

    /**
     * Display all tasks
     * @param list list of tasks
     */
    public void displayAllTasks(List<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(prefix + (i + 1) + ". " + list.get(i));
        }
    }

    /**
     * Display after adding a task
     * @param size task list size after adding the task
     * @param task task added
     */
    public void displayAfterAdd(int size, Task task) {
        System.out.println(added);
        displayTask(task);
        displayListSize(size);
    }

    /**
     * Display after done a task
     * @param task task done
     */
    public void displayAfterDone(Task task) {
        System.out.println(markAsDone);
        displayTask(task);
    }

    /**
     * Display after delete a task
     * @param size task list size after deleting the task
     * @param task task deleted
     */
    public void displayAfterDelete(int size, Task task) {
        System.out.println(removeTask);
        System.out.println(prefix + task);
        displayListSize(size);
    }

}
