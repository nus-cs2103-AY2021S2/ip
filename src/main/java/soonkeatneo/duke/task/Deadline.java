package soonkeatneo.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Implementation for tasks with a specified end-date.
 *  @author Soon Keat Neo
 *  @version CS2103T AY20/21 Sem 2 iP
 */

public class Deadline extends Task {
    private final LocalDate lastDate;

    /**
     * Creates a new Deadline object with the given parameters.
     * @param taskName Description of the Deadline object
     * @param lastDate Actual deadline of the object
     */
    public Deadline(String taskName, String lastDate) {
        super(taskName, "D");
        this.lastDate = LocalDate.parse(lastDate);
    }

    /**
     * Returns the date of the instance.
     * @return date of the object
     */
    public LocalDate getDate() {
        return this.lastDate;
    }

    @Override
    public String toString() {
        String dayOfWeek = this.lastDate.getDayOfWeek().toString().toLowerCase();
        dayOfWeek = dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1);
        String date = this.lastDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + dayOfWeek + ", " + date + ")";
    }
}
