package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task markTaskAsDone(int index) {
        return tasks.get(index).markAsDone();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public TaskList findTasks(String keywords) {
        TaskList foundTasks = new TaskList();
        for(Task task : tasks) {
            if(task.description.contains(keywords)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int getSize() {
        return tasks.size();
    }
}
