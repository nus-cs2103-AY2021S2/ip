package duke;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task t) {
        this.list.add(t);
    }

    public void deleteTask(int index) {
        this.list.remove(index);
    }

    public int getSize() {
        return this.list.size();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public Task getItem(int index) {
        return this.list.get(index);
    }

}
