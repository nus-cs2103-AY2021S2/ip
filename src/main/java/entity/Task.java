package main.java.entity;

public class Task {
    protected String name;
    protected boolean isDone;
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String toFileString() {
        return "";
    }

    public boolean markAsDone() {
        this.isDone = true;
        return true;
    }

    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }
}
