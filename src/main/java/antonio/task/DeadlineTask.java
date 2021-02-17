package antonio.task;

import java.time.LocalDate;
/**
 * Represents a Deadline task.
 */
public class DeadlineTask extends Task {

    private final LocalDate deadline;
    private final String time;

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
     * Gets the deadline date of the task.
     * @return The deadline date of the task.
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Gets the time of the task.
     * @return The time of the task.
     */
    public String getTime() {
        return time;
    }

    /**
     * Checks if the this task is clashing with another task.
     * @param task Task to be checked if clashing with.
     * @return True if the tasks are clashing.
     */
    public boolean isClashingSchedule(DeadlineTask task) {
        boolean isSameDay = this.deadline.equals(task.getDeadline());
        boolean isSameTime = Integer.parseInt(this.time) == Integer.parseInt(task.getTime());
        return isSameDay && isSameTime;
    }

    /**
     * Serializes deadline into a string format for local storage.
     * @return The serialized deadline.
     */
    public String serializeDeadline() {
        return deadline.toString() + " | " + time;
    }

    /**
     * Pads the time string with zeroes to a 24 hour format.
     * @param time to be padded to a 24 hour format.
     * @return The padded time string.
     */
    private String timeToString(String time) {
        String padding;
        if (time.length() == 1) {
            padding = String.format("%03d", 0);
        } else if (time.length() == 2) {
            padding = String.format("%02d", 0);
        } else if (time.length() == 3) {
            padding = String.format("%01d", 0);
        } else {
            padding = "";
        }
        return padding + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.checkBoxToString() + description + "\n(by: " + deadline.getDayOfMonth()
                + " " + deadline.getMonth() + " " + deadline.getYear() + " " + timeToString(time) + "HRS)";
    }
}
