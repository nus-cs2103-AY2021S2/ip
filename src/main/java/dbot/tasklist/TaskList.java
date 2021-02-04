package dbot.tasklist;

import dbot.task.Task;

import java.util.ArrayList;

/**
 * Stores a List of Tasks and manages getting, adding, removing, and encoding Tasks.
 *
 * Encoding Tasks involves converting a TaskList instance to a String representation that can be written
 * to a file and loaded from in the future.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Initialises a TaskList with space for 100 entries.
     *
     * The number of entries that can be stored will automatically increase beyond 100 if
     * the user attempts to add more Tasks.
     */
    public TaskList() {
        this(100);
    }

    /**
     * Initialises a TaskList with space for the specified number of initial entries.
     *
     * The number of entries that can be stored will automatically increase  if the user attempts to add
     * more Tasks.
     *
     * @param initialSize The initial capacity of the TaskList.
     */
    public TaskList(int initialSize) {
        super(initialSize);
    }

    /**
     * Returns a string representation of the TaskList that can be written to a file and loaded in the future
     * to generate a similar TaskList object.
     *
     * The encode method stores all the necessary information to create a replica TaskList whereby
     * every Task contained has the same properties as the TaskList that was encoded.
     *
     * @return A String representation of this TaskList that can be stored and/or used to create a replicate TaskList.
     */
    public String encode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : this) {
            String taskName = task.getClass().getSimpleName().toLowerCase();
            boolean isDone = task.getDone();
            String fullDescription = task.getFullDescription();
            stringBuilder.append(taskName + "|" + isDone + "|" + fullDescription + "\n");
        }
        return stringBuilder.toString();
    }
}
