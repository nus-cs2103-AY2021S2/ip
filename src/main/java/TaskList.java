package main.java;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    public ArrayList<Task> getTaskList() {
        return lst;
    }

    public void addTask(Task task) {
        lst.add(task);
    }

    public void deleteTask(int index) {
        lst.remove(index);
    }

    public int getListSize() {
        return this.lst.size();
    }

    public Task getTask(int index) {
        return this.lst.get(index);
    }
}
