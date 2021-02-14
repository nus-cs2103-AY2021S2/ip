package task;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;

public abstract class Task {
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

    public String getDescription() {
        return description;
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

    public abstract TaskType getTaskType();

    public abstract String getCommandString();

    public abstract String toSaveString();
}
