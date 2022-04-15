package task;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;
import duke.Tag;

/**
 * Parent class of Todo, Event, Deadline
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected List<String> tags;

    /**
     * Creates a Task.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    public Task(String description, boolean isDone, List<String> tags) {
        this.description = description;
        this.isDone = isDone;
        this.tags = tags;
    }

    public String fileFormat() {
        return this.getClass().toString() + " | " + (isDone ? "1 | " : "0 | ") + this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }


    public Task markAsDone() throws DukeException {
        if (isDone) {
            throw new DukeException("This Task has already been marked as done!");
        }
        return new Task(description, true, tags);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void deleteTag(String tag) {
        tags.remove(tag);
    }

    @Override
    public String toString() {
        return description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void handleTag(Tag mode, String tag) {
        switch(mode) {
        case ADD:
            addTag(tag);
            break;
        case DELETE:
            deleteTag(tag);
            break;
        default:
            throw new AssertionError();
        }
    }
}
