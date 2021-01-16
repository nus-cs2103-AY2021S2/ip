package main.java.entity;

public class Task {
    protected String name;
    protected boolean isDone;
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public boolean markAsDone() {
        this.isDone = true;
        return true;
    }

    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }
}
