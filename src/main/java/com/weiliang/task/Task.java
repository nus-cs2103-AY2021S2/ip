package com.weiliang.task;

public class Task {

    protected String task;
    protected boolean isComplete;

    public Task(String task) {
        this.task = task;
    }

    public void markComplete() {
        this.isComplete = true;
    }

    public String getDescription() {
        return task;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public String toFormattedString() {
        return "T | " + (isComplete ? 1 : 0) + " | " + task;
    }

    @Override
    public String toString() {
        return "[T][" + (isComplete ? "X" : " ") + "] " + task;
    }

}
