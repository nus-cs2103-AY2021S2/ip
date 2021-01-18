package main.java.subfiles;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

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
        tasks.add(new Task(s));
        System.out.println("added: " + s);
    }

    public void printTasks() {
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task task = tasks.get(i - 1);
            char isDone = task.isDone() ? 'X' : ' ';
            System.out.println(i + ". [" + isDone + "] " + task.toString());
        }
    }

    public void markDone(int index) {
        tasks.get(index).setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + tasks.get(index).toString());
    }
}
