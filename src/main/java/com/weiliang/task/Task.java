package com.weiliang.task;

public class Task {

    protected String task;
    protected boolean completed;

    public Task(String task) {
        this.task = task;
    }

    public void complete() {
        this.completed = true;
    }

    public String getDescription() {
        return task;
    }

    public String toFormattedString() {
        return "T | " + (completed ? 1 : 0) + " | " + task;
    }

    @Override
    public String toString() {
        return "[T][" + (completed ? "X" : " ") + "] " + task;
    }

}
