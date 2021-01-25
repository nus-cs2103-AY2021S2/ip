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

    public List<Task> get() {
        return this.tasks;
    }

    public void add(Task task) {
        tasks.add(task);
        storage.writeToFile(this);
    }

    public void delete(Task task) {
        tasks.remove(task);
        storage.writeToFile(this);
    }

    public Task find(int i) {
        return tasks.get(i);
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
        return tasksContent.trim();
    }
}
