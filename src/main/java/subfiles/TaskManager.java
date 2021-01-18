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

    private void addedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void addTask(String s) {
        tasks.add(new Task(s));
        addedTask();
    }

    public void addTask(String s, char type) {
        if (type == 't') {
            s = s.substring(5);
            tasks.add(new ToDo(s));
        } else if (type == 'd') {
            String[] sArray = s.split("/", 2);
            s = sArray[0].substring(9, sArray[0].length() - 1);
            String t = sArray[1].substring(3);
            tasks.add(new Deadline(s, t));
        } else {
            String[] sArray = s.split("/", 2);
            s = sArray[0].substring(6, sArray[0].length() - 1);
            String t = sArray[1].substring(3);
            tasks.add(new Event(s, t));
        }
        addedTask();
    }

    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task task = tasks.get(i - 1);
            System.out.println(i + ". " + task.toString());
        }
    }

    public void markDone(int index) {
        tasks.get(index).setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
    }
}
