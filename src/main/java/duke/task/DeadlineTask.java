package duke.task;

import java.time.LocalDate;
/**
 * Represents a Deadline task.
 */
public class DeadlineTask extends Task {

    private LocalDate deadline;
    private String time;

    /**
     * Constructor for Deadline task.
     * @param description Name of the command.
     * @param id ID of task
     * @param status Status of task completion.
     * @param deadline Date of deadline for the task.
     * @param time Time of deadline for the task.
     */
    public DeadlineTask(String description, int id, int status, LocalDate deadline, String time) {
        super(description, id);
        super.isDone = status > 0;
        this.deadline = deadline;
        this.time = time;
    }

    /**
     * Serializes deadline into a string format for local storage.
     * @return Serialized deadline.
     */
    public String serializeDeadline() {
        return deadline.toString() + " | " + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.checkBoxToString() + description + " (by: " + deadline.getDayOfMonth()
                + " " + deadline.getMonth() + " " + deadline.getYear() + " " + time + "HRS)";
    }
}
