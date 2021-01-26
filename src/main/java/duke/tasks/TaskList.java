package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public int size() {
        return this.list.size();
    }

    public Task markCompleted(int index) {
        Task task = this.list.get(index);
        task.setCompleted();
        return task;
    }

    public Task delete(int index) {
        Task task = this.list.get(index);
        this.list.remove(index);
        return task;
    }
}
