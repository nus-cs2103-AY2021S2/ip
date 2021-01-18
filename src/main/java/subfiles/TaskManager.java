package main.java.subfiles;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<String> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void addTask(String s) {
        tasks.add(s);
        System.out.println("added: " + s);
    }

    public void printTasks() {
        for (int i = 1; i < tasks.size() + 1; i++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }
}
