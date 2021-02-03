package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    /** Date of event **/
    private LocalDate atDate;
    /** Time of event **/
    private String time;

    /**
     * Constructs an EventTask object.
     * @param description Descriptions of the event
     * @param isDone Boolean on whether the event is attended already
     * @param atDate Date of event
     * @param time Time of event
     */
    public EventTask(String description, boolean isDone,
                     LocalDate atDate, String time) {
        super(description);
        super.isDone = isDone;
        this.atDate = atDate;
        this.time = time;
    }

    /**
     * Returns a String that provides details of an EventTask
     * with a unique header.
     * @return String
     */
    @Override
    public String toString() {
        if (this.atDate != null) {
            return "[E] " + super.toString() + " (by: "
                    + this.atDate.format(
                            DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                    + ", time: " + time + ")";
        }
        //error prone line below
        return "[E] " + super.toString() + " (by: " + this.atDate + ")";
    }

    /**
     * Returns a String that provides details of an EventTask.
     * @return String
     */
    @Override
    public String getTaskDetails() {
        String divider = " | ";
        return "E" +  divider
                + (isDone ? "1" : "0") + divider
                + description + divider
                + atDate + divider + time;
    }
}
