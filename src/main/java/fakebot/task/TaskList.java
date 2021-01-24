package fakebot.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks;
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int i) {
        tasks.remove(i);
    }

    public int getSize() {
        return tasks.size();
    }

    public List<Task> find(String search) {
        List<Task> foundTask = new ArrayList<>();
        
        return foundTask;
    }
}
