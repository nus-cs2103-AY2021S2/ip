package duke.tasks;

import duke.exceptions.DukeExceptionIllegalArgument;

/**
 * Task class.
 *
 * Bulk of the definition comes from the original webpage by CS2103 at
 * { @link https://nus-cs2103-ay2021s2.github.io/website/schedule/week2/project.html }.
 * General class representing all tasks. Should not be instantiated directly.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of Task
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Constructor for Task.
     *
     * @param description Description of Task.
     * @param isDone Whether task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks task as done.
     *
     * No exception thrown if task is already marked as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns description of Task.
     *
     * @return Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns status icon representing completion of Task.
     *
     * @return Tick or cross.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713": "\u2718");
    }

    /**
     * Returns Task by parsing a task string.
     *
     * @param s Task string.
     * @return Task.
     * @throws DukeExceptionIllegalArgument If invalid task string supplied.
     */
    public static Task parseFileString(String s) throws DukeExceptionIllegalArgument {
        String[] args = s.split("\t");
        boolean isDone = args[1].equals("1");

        Task task;
        switch (args[0]) {
        case "E":
            task = Event.parse(args[2] + " /at " + args[3]);
            break;
        case "T":
            task = Todo.parse(args[2]);
            break;
        case "D":
            task = Deadline.parse(args[2] + " /by " + args[3]);
            break;
        default:
            throw new DukeExceptionIllegalArgument("Incorrect task list data.");
        }
        if (isDone) {
            task.setDone();
        }
        return task;
    }

    /**
     * For pretty printing on stdout.
     *
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * For minified printing into file.
     *
     * @return String representation of Task.
     */
    public String toFileString() {
        return toString();
    }
}
