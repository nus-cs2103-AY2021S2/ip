package ekud.task;

import java.util.LinkedList;

public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or cross symbols
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? 'X' : ' ', description);
    }

    public LinkedList<String> export() {
        LinkedList<String> list = new LinkedList<>();
        list.add(String.valueOf(isDone));
        list.add(description);
        return list;
    }
}
