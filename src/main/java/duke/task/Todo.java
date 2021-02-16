package duke.task;

import duke.tag.Tag;

import java.util.ArrayList;

/**
 * Representation of most basic task with just a task description
 */
public class Todo extends Task {
    public Todo(String task, ArrayList<Tag> tags) {
        super(task, tags);
    }

    public Todo(boolean done, String task, ArrayList<Tag> tags) {
        super(task, tags);
        this.isDone = done;
    }

    /**
     * Returns a String representation of this Todo for storage to a file.
     * @return String representation
     */
    public String toFileString() {
        return "T | " + this.isDone + " | " + this.task
                + " | " + Tag.tagListToString(this.tags);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
