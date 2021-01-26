package main.java;

import main.java.entity.Task;
import java.util.List;
import java.util.Scanner;

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

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void greeting() {
        System.out.println("This is \n" + LOGO);
        System.out.println(GREETINGS);
    }

    public void displayExit() {
        System.out.println(PREFIX + "Bye, until next time!");
    }

    public void displayLine() {
        System.out.println("________________________");
    }

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

    public void displayError(String message) {
        System.out.println("Duke is sorry about it, but we have encountered an error...");
        System.out.println(message);
    }

    public void displayTask(Task task) {
        System.out.println(PREFIX + task);
    }

    public void displayListSize(int size) {
        System.out.println(PREFIX + "Now you have " + size + " tasks in the list.");
    }

    public void displayEmptyList() {
        System.out.println(DISPLAY_EMPTY_LIST);
    }

    public void displayAllTasks(List<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(PREFIX + (i + 1) + ". " + list.get(i));
        }
    }

    public void displayAfterAdd(int size, Task task) {
        System.out.println(DISPLAY_ADDED);
        displayTask(task);
        displayListSize(size);
    }

    public void displayAfterDone(Task task) {
        System.out.println(DISPLAY_DONE);
        displayTask(task);
    }

    public void displayAfterDelete(int size, Task task) {
        System.out.println(DISPLAY_RENAME);
        System.out.println(PREFIX + task);
        displayListSize(size);
    }

}
