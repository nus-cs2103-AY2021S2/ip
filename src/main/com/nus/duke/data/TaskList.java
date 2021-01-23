package com.nus.duke.data;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int addTask(Task task) {
        this.tasks.add(task);
        return this.tasks.size();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task removeTask(int index) throws IndexOutOfBoundsException {
        return this.tasks.remove(index);
    }

    public Task markAsDone(int index) throws IndexOutOfBoundsException {
        Task doneTask = this.tasks.get(index);
        doneTask.markAsDone();
        return doneTask;
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }
}
