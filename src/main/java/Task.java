package main.java;

public class Task {

    protected String description;
    protected Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void done() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String done = this.isDone? "X" : " ";
        return "[" + done + "] " + this.description;
    }


}
