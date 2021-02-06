package main.java;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import main.java.entity.Task;

/**
 * User Interface Management class that helps facilitate
 * interaction with user
 * particularly for user input passing
 * and display query result to user
 */
public class Ui {
    static final String PREFIX = "    ";
    static final String DISPLAY_EMPTY_LIST = PREFIX + "Currently there is nothing on your list.";
    static final String DISPLAY_ADDED = PREFIX + "Got it. I've added this task:";
    static final String DISPLAY_DONE = PREFIX + "Nice! I've mark this task as done:";
    static final String DISPLAY_REMOVE = PREFIX + "Noted, I've removed the following task(s):";
    static final String GREETINGS = "Dear user, welcome to the world of duke!";
    static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc;

    /**
     * Creates a Ui instance
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Show greetings display
     */
    public String greeting() {
        return "This is \n" + LOGO + "\n" + GREETINGS;
    }

    /**
     * Show exit display
     */
    public String displayExit() {
        return PREFIX + "Bye, until next time!";
    }

    /**
     * Show a horizontal line
     */
    public String displayLine() {
        return "________________________";
    }

    /**
     * Read a line of user input
     * @return a string of the line
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Display a general wrong command
     */
    public String displayWrongCommand() {
        return PREFIX + "Sorry, but we could not parse your input\n"
                + PREFIX + "Please recheck its validity...";
    }

    /**
     * Display a wrong command message
     * @param message customized input message
     */
    public String displayWrongCommand(String message) {
        return PREFIX + message + "\n"
                + PREFIX + "Sorry, but we could not parse your input\n"
                + PREFIX + "Please recheck its validity...";
    }

    /**
     * Display an input index out of range error message
     * @param index the given index
     */
    public String displayOutOfRange(int index) {
        return PREFIX + "There is no such index " + index + " in the task list.";
    }

    /**
     * Display Error with particular error message
     * @param message particular error message
     */
    public String displayError(String message) {
        return "Duke is sorry about it, but we have encountered an error...\n"
                + message;
    }

    /**
     * Display a task
     * @param task task to be displayed
     */
    public String displayTask(Task task) {
        return PREFIX + task.toString();
    }

    /**
     * Display info regarding current task list size
     * @param size list size
     */
    public String displayListSize(int size) {
        return PREFIX + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Display when there is nothing in task list
     */
    public String displayEmptyList() {
        return DISPLAY_EMPTY_LIST;
    }

    /**
     * Display all tasks
     * @param list list of tasks
     */
    public String displayAllTasks(List<Task> list) {
        String display = "";
        AtomicInteger i = new AtomicInteger(1);
        display = list.stream().reduce("", (x, y) -> (x + PREFIX + (i.getAndIncrement()) + ". " + y.toString() +"\n"), (x, y) -> x + y);
        return display;
    }

    /**
     * Display after adding a task
     * @param size task list size after adding the task
     * @param task task added
     * @return
     */
    public String displayAfterAdd(int size, Task task) {
        String display = DISPLAY_ADDED;
        display += "\n" + displayTask(task);
        display += "\n" + displayListSize(size);
        return display;
    }

    /**
     * Display after done a task
     * @param task task done
     */
    public String displayAfterDone(Task task) {
        String display = DISPLAY_DONE + "\n";
        display += displayTask(task);
        return display;
    }

    /**
     * Display after delete a task
     * @param size task list size after deleting the task
     * @param task task deleted
     */
    public String displayAfterDelete(int size, List<Task> taskList) {
        String display = DISPLAY_REMOVE;
        for (Task task : taskList) {
            display += "\n" + PREFIX + task.toString();
            display += "\n" + displayListSize(size);
        }
        return display;
    }

    /**
     * Display search result based on given result task list
     * @param result task list found
     */
    public String displaySearchResult(List<Task> result) {
        String display;
        if (result.isEmpty()) {
            display = PREFIX + "Sorry, there is no task found.";
        } else {
            display = PREFIX + "We found " + result.size() + " results:\n";
            display = result.stream().reduce(display, (x, y) -> (x + PREFIX + y.toString() + "\n"), (x, y) -> x + y);
        }
        return display;
    }
}
