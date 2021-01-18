package main.java.subfiles;

public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[ ][" + (isDone ? "X" : " ") + "] " + name;
    }
}
