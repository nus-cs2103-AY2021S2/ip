package duke;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }


    public Task deleteTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    public Task setDone(int index) {
        tasks.get(index).setDone();
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }
}
