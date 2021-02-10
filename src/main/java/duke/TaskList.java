package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Wraps an ArrayList as a TaskList meant for Duke.
 */
public class TaskList extends ArrayList<Task> {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
}
