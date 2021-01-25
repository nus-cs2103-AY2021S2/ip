package duke.task;

import duke.Ui;

import java.time.LocalDate;

/**
 * Skeleton class for all tasks.
 */
public abstract class Task {
    String description;
    boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Marks the task as done and prints out to console that task is done.
     */
     public void done() {
        this.isDone = true;
        Ui.printWithStyle(new String[] {"Nice! I've marked this task as done:", this.toString()});
    }

    public boolean isDescriptionContainsString(String input) {
         return this.description.contains(input);
    }

    public LocalDate getDate() {
         return LocalDate.MIN;
    }

    @Override
    public String toString() {
        return isDone ? "[X] " + this.description : "[ ] " + this.description;
    }
}
