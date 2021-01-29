package Duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        Task removed = tasks.remove(index);
        return removed;
    }

    public void doneTask(int index) {
        Task task = tasks.get(index);
        task.checkTask();
        tasks.add(index, task);
        tasks.remove(index + 1);
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        Task task = this.tasks.get(index);
        return task;
    }
}
