package main.java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = new ArrayList<Task>();
    }

    TaskList(List<Task> tasks, Storage storage) {
        this(storage);
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
        Duke.ollySpeak(task.addMessage + (task.addMessage == null ? "" : " ") + "I've added:");
        System.out.println(task);
        Duke.ollySpeak("You now have " + this.size() + " tasks at hand.");

        storage.writeToFile(this.tasks);
    }

    public void delete(Task task) {
        tasks.remove(task);
        Duke.ollySpeak("Aww man.. I've removed this task:");
        System.out.println(task);
        Duke.ollySpeak("Now you have " + this.size() + " tasks left.");

        storage.writeToFile(this.tasks);
    }

    public List<Task> getByDate(LocalDate date) {
        List<Task> tempTask = new ArrayList<Task>();
        for (Task task: tasks) {
            if (task.date != null && task.date.isEqual(date)) {
                tempTask.add(task);
            }
        }
        return tempTask;
    }

    public int size() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        String tasksContent = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksContent += (i+1 + ". " + tasks.get(i) + "\n");
        }
        return tasksContent;
    }
}
