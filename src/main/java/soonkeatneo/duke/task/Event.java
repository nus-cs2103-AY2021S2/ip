package soonkeatneo.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Implementation for tasks with a given start-time and end-time.
 *  @author Soon Keat Neo
 *  @version CS2103T AY20/21 Sem 2 iP
 */

public class Event extends Task {
    private final LocalDate startAndEnd;


    public Event(String taskName, String startAndEnd) {
        super(taskName, "E");
        this.startAndEnd = LocalDate.parse(startAndEnd);
    }

    /**
     * Returns the date of the instance of the event
     * @return the event date
     */
    public LocalDate getDate() {
        return this.startAndEnd;
    }

    @Override
    public String toString() {
        String dayOfWeek = this.startAndEnd.getDayOfWeek().toString().toLowerCase();
        dayOfWeek = dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1);
        String date = this.startAndEnd.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + dayOfWeek + ", " + date + ")";
    }
}
