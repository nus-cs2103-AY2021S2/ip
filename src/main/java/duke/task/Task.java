package duke.task;

import duke.tag.Tag;

import java.util.ArrayList;

public abstract class Task {
    protected String task;
    protected boolean isDone;
    protected ArrayList<Tag> tags;

    Task(String task, ArrayList<Tag> tags) {
        this.task = task;
        this.isDone = false;
        this.tags = tags;
    }

    public void doTask() {
        this.isDone = true;
    }

    public String getTask() {
        return this.task;
    }

    public abstract String toFileString();

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.task + " " + Tag.tagListToString(tags);
        } else {
            return "[ ] " + this.task + " " + Tag.tagListToString(tags);
        }
    }
}
