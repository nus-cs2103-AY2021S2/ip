package task;

import java.util.HashMap;

public abstract class Task {
    protected static final char saveDelimiter = '|';
    private final String description;
    private boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean isDone) {
        this.description = desc;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return blank or tick symbol
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toSaveFormat() {
        StringBuilder savedString = new StringBuilder();
        savedString.append(commandString());
        savedString.append(" ");
        savedString.append(description);
        savedString.append((isDone ? "/done " : ""));

        saveArgs().forEach((k, v) -> savedString.append("/").append(k).append(" ").append(v));

        return savedString.toString();
    }

    protected abstract HashMap<String, String> saveArgs();

    public abstract String commandString();
}
