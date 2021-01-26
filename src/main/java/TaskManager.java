package main.java;
import main.java.entity.*;
import java.util.List;

public class TaskManager {
    private List<Task> list;
    private Storage storage;

    public TaskManager(String filepath) {
        storage = new Storage(filepath);
        list = storage.readAll();
    }

    public int size() {
        return this.list.size();
    }

    public void addTask(Task task) {
        list.add(task);
        storage.updateFile(list);
    }

    public Task done(int index) {
        Task task = list.get(index);
        task.markAsDone();
        storage.updateFile(list);
        return task;
    }

    public Task deleteTask(int index) {
        Task task = list.remove(index);
        storage.updateFile(list);
        return task;
    }

    public List<Task> getList() {
        return this.list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean indexWithinRange(int index) {
        return index < size() && index >= 0;
    }
}
