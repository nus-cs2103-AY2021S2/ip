package com.jetbrains;

class Task {
    String task;
    boolean isDone;

    Task() {
    }

    Task(String task) {
        this.task = task;
        isDone = false;
    }

    Task markDone() {
        isDone = true;
        return this;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + task;
        } else {
            return "[ ] " + task;
        }
    }
}
