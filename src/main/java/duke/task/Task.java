package duke.task;

import java.time.LocalDate;
import java.util.Locale;

import duke.Helper;

/**
 * Skeleton class for all tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes an undone task with the given description.
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
    * Marks the task as done and prints out to console that task is done.
    */
    public String done() {
        this.isDone = true;
        return Helper.formatStrings("Nice! I've marked this task as done:", this.toString());
    }

    public boolean isDescriptionContainsString(String input) {
        return this.description.toLowerCase().contains(input.toLowerCase());
    }

    public LocalDate getDate() {
        return LocalDate.MIN;
    }

    @Override
    public String toString() {
        String doneBracket = isDone ? "[X]" : "[ ]";
        return doneBracket + " " + this.description;
    }
}
