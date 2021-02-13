package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for tasks with a deadline.
 */
public class Deadline extends Task {
    LocalDate deadLine;

    /**
     * Constructs an undone Deadline java.duke.controller.task with a deadline and a name.
     *
     * @param taskName Name of the Deadline java.duke.controller.task.
     * @param deadLine Deadline of the Deadline java.duke.controller.task.
     */
    public Deadline(String taskName, LocalDate deadLine) {
        super(taskName);
        assert deadLine != null;
        this.deadLine = deadLine;
    }

    /**
     * Constructs a Deadline java.duke.controller.task with a deadline, a name and status.
     *
     * @param taskName Name of the Deadline java.duke.controller.task.
     * @param isDone Status of the java.duke.controller.task, done or not done.
     * @param deadLine Deadline of the Deadline java.duke.controller.task.
     */
    public Deadline(String taskName, boolean done, LocalDate deadLine) {
        super(taskName, done);
        assert deadLine != null;
        this.deadLine = deadLine;
    }

    /**
     * Returns a string representation of the Deadline java.duke.controller.task.
     *
     * @return A string representation of the Deadline java.duke.controller.task.
     */
    public String toString() {
        String formattedDate = this.deadLine.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    /**
     * Parse the java.duke.controller.task to comply with CSV format.
     *
     * @return A string that complies with CSV format.
     */
    public String parseToCsvRow() {
        return "D," + super.isDone() + "," + super.getTaskName() + "," + this.deadLine;
    }

    /**
     * Returns the deadline of the Task.
     *
     * @return Deadline of the Task.
     */
    public LocalDate getTaskTime() {
        return deadLine;
    }
}
