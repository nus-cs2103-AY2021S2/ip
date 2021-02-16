package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /**
     * Creates a new deadline task
     * @param name deadline name
     * @param date deadline due date
     */
    public Deadline(String name, LocalDate date) {
        super(name, "Make sure you meet this deadline!");
        this.date = date;
    }

    /**
     * Overloads the Deadline(String name, LocalDate date) method, with new status parameter.
     * This can be used if status is required to be defined.
     * @param name deadline name
     * @param date deadline date
     * @param status deadline status (done, not done)
     */
    public Deadline(String name, LocalDate date, Boolean status) {
        this(name, date);
        this.status = status;
    }

    /**
     * Returns a formatted string required for storing Deadline task to .txt file
     * @return formatted string of Task for file
     */
    @Override
    public String toFileString() {
        return "D," + (this.status ? "1" : "0") + "," + this.name + ","
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
