package core.task;

import java.io.Serializable;

public class Task implements Serializable {
    protected String taskDescription;
    protected boolean isDone;
    private final int taskUID;
    private static int UID_CURR;

    static {
        UID_CURR = 1;
    }

    {
        taskUID = UID_CURR++;
    }

    public int getTaskUid() {
        return taskUID;
    }

    public Task(String desc, boolean isDone) {
        this.taskDescription = desc;
        this.isDone = isDone;
    }

    public Task(String desc) {
        this(desc, false);
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getTaskDescription();
    }

    public boolean contains(String word) {
        return taskDescription.toLowerCase().contains(word.toLowerCase());
    }

}
