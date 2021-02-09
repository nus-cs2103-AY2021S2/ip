package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;

/**
 * Deadline class which is an extension of Task which also contains deadline (date and time) of the task.
 */
public class Deadline extends Task {

    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructor for Deadline class
     *
     * @param description of the Deadline task
     * @param date        that the task is due
     * @param time        that the task is due
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description, TaskType.DEADLINE);
        this.date = date;
        this.time = time;
    }

    /**
     * Constructor for the Deadline class with completion status specified
     *
     * @param description of the Deadline task
     * @param date        that the task is due
     * @param time        that the task is due
     * @param completed   <code>true</code> if the event is completed, <code>false</code> otherwise
     */
    public Deadline(String description, LocalDate date, LocalTime time, Boolean completed) {
        super(description, TaskType.DEADLINE, completed);
        this.date = date;
        this.time = time;
    }

    /**
     * Constructor for the Deadline class whereby only date is given for the deadline but not time
     *
     * @param description of the Deadline task
     * @param date        that the task is due
     */
    public Deadline(String description, LocalDate date) {
        super(description, TaskType.DEADLINE);
        this.date = date;
        this.time = null;
    }

    /**
     * Constructor for the Deadline class whereby only date is given for the deadline but not time
     * with completion status specified
     *
     * @param description of the Deadline task
     * @param date        that the task is due
     * @param completed   <code>true</code> if the event is completed, <code>false</code> otherwise
     */
    public Deadline(String description, LocalDate date, Boolean completed) {
        super(description, TaskType.DEADLINE, completed);
        this.date = date;
        this.time = null;
    }

    /**
     * Parse command meant to create Deadline tasks
     *
     * @param command to be parsed
     * @return Deadline object created based on command issued
     * @throws DukeException if command is empty or missing "/by" delimiter which provides deadline, deadline not
     *                       provided in an acceptable format or command is empty
     */
    public static Deadline parseDeadline(String command) throws DukeException {
        if (command.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (!command.contains("/by")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline must contain a time.");
        }
        String[] partitioned = command.split("/by");
        String desc = partitioned[0].strip();
        String[] datetime = partitioned[1].strip().split(" ");
        try {
            if (datetime.length == 2) {
                return new Deadline(desc, LocalDate.parse(datetime[0]), LocalTime.parse(datetime[1]));
            } else {
                return new Deadline(desc, LocalDate.parse(datetime[0]));
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! The datetime description of a deadline must be either of the form"
                    + "'YYYY-MM-DD' or 'YYYY-MM-DD hh:mm'");
        }

    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());
        output.append(" (by: ");
        output.append(this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        if (this.time != null) {
            output.append(" ");
            output.append(this.time.format(DateTimeFormatter.ofPattern("h:mm a")));
        }
        output.append(")");
        return output.toString();
    }

    /**
     * Returns the String formatted entry for writing to disk
     *
     * @return String formatted entry to be written to disk by Storage objects
     */
    public String storageEntry() {
        StringBuilder output = new StringBuilder(super.storageEntry());
        output.append("|");
        output.append(this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        if (this.time != null) {
            output.append(" ");
            output.append(this.time.format(DateTimeFormatter.ofPattern("HH:mm")));
        }
        return output.toString();
    }
}
