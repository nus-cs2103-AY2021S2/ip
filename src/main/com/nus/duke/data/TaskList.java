package com.nus.duke.data;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
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

    public List<? extends Task> getList() {
        return this.tasks;
    }
}
