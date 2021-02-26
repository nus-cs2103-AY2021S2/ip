package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Responsible for containing deadline tasks.
 */
public class DeadlineTask extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructs a DeadlineTask with the given description and deadline.
     *
     * @param description Description of task.
     * @param date Deadline of task.
     */
    public DeadlineTask(String description, LocalDate date) {
        super(description, 'D');
        this.date = date;
        this.time = null;
    }

    /**
     * Constructs a DeadlineTask with the given description, deadline and time.
     *
     * @param description Description of task.
     * @param date Deadline of task.
     * @param time Deadline timing of task.
     */
    public DeadlineTask(String description, LocalDate date, LocalTime time) {
        super(description, 'D');
        this.date = date;
        this.time = time;
    }

    private String getDateSaveString() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private String getTimeSaveString() {
        return this.time == null
                ? ""
                : " | " + this.time.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Returns the format of string of task to be saved.
     *
     * @return String of task to be saved.
     */
    @Override
    public String getSaveString() {
        return super.getSaveString() + " | " + getDateSaveString() + getTimeSaveString();
    }

    /**
     * Returns the string of the task.
     *
     * @return string of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + getDateString()
                + getTimeString() + ")";
    }

    private String getDateString() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    private String getTimeString() {
        return this.time == null
                ? ""
                : " " + this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }
}
