package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** A task with a due date */
public class Deadline extends Task {
    /** Date and time when the Deadline task needs to be completed */
    private LocalDateTime dateTime;

    /**
     * Constructor for a Deadline task
     * @param desc Description of the task
     * @param dateTime Date and time when the task needs to be completed
     */
    public Deadline(String desc, LocalDateTime dateTime) {
        super(desc, false);
        this.dateTime = dateTime;
    }

    /**
     * Alternate constructor for a Deadline task whereby you can indicate it's completion status
     * @param desc Description of the task
     * @param dateTime Date and time when the task needs to be completed
     * @param isDone Completion status of the task
     */
    public Deadline(String desc, LocalDateTime dateTime, boolean isDone) {
        super(desc, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns the description of the Deadline
     * @return Description of the Deadline
     */
    @Override
    public String getDesc() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, EEE ha");
        return this.desc + " (By: " + this.dateTime.format(formatter) + ")";
    }

    /**
     * Returns a letter symbol for the Deadline
     * @return Letter symbol for the Deadline
     */
    @Override
    public String getTypeSymbol() {
        return "D";
    }

    /**
     * Returns the Deadline's details in a format to be saved into the hard disk
     * @return Deadline's details in a savable format
     */
    @Override
    public String toSaveInfoString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");
        return this.getTypeSymbol() + " | " + (this.isDone ? "1" : "0") + " | " + this.desc + " | " +
                this.dateTime.format(formatter);
    }

    /**
     * Returns the date and time at which the Deadline is due
     * @return Due date and time for the Deadline
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
}
