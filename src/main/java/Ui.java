package main.java;

import main.java.entity.Task;

import java.util.List;
import java.util.Scanner;

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

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void greeting() {
        System.out.println("This is \n" + logo);
        System.out.println(greetings);
    }

    public void displayLine() {
        System.out.println("________________________");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void displayWrongCommand(String message) {
        System.out.println(prefix + message);
        System.out.println(prefix + "Sorry, but we could not parse your input");
        System.out.println(prefix + "Please recheck its validity...");
    }

    public void displayOutOfRange(int index) {
        System.out.println(prefix + "There is no such index " + index + " in the task list.");
    }

    public void displayWrongCommand() {
        System.out.println(prefix + "Sorry, but we could not parse your input");
        System.out.println(prefix + "Please recheck its validity...");
    }

    public void displayError(String message) {
        System.out.println("Duke is sorry about it, but we have encountered an error...");
        System.out.println(message);
    }

    public void displayTask(Task task) {
        System.out.println(prefix + task);
    }

    public void displayListSize(int size) {
        System.out.println(prefix + "Now you have " + size + " tasks in the list.");
    }

    public void displayEmptyList() {
        System.out.println(displayEmptyList);
    }

    public void displayAllTasks(List<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(prefix + (i + 1) + ". " + list.get(i));
        }
    }

    public void displayAfterAdd(int size, Task task) {
        System.out.println(added);
        displayTask(task);
        displayListSize(size);
    }

    public void displayAfterDone(Task task) {
        System.out.println(markAsDone);
        displayTask(task);
    }

    public void displayAfterDelete(int size, Task task) {
        System.out.println(removeTask);
        System.out.println(prefix + task);
        displayListSize(size);
    }

}
