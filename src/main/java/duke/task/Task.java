package duke.task;

import duke.Ui;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Skeleton class for all tasks.
 */
public abstract class Task {
    String task;
    boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }


    /**
     * Marks the task as done and prints out to console that task is done.
     */
     public void done() {
        this.isDone = true;
        Ui.printWithStyle(new String[] {"Nice! I've marked this task as done:", this.toString()});
    }

    public LocalDate getDate() {
         return LocalDate.MIN;
    }

    @Override
    public String toString() {
        return isDone ? "[X] " + this.task : "[ ] " + this.task;
    }
}
