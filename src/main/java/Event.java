import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task in the form of an event which has a date,
 * an event starting time and an event ending time.
 */

public class Event extends Task {
    private LocalDate date;
    private LocalTime startTime; //in 24h format
    private LocalTime endTime;

    /**
     * Constructor takes in four parameters <code>description</code>,
     * <code>date</code>, <code>startTime</code> and <code>endTime</code>.
     * @param description the description of the event
     * @param date the date of the event
     * @param startTime the starting time of the event
     * @param endTime the ending time of the event
     */
    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the date of the event
     * @return the date of the event
     */
    @Override
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Generates the text of this task in the proper format to be stored
     * into the text file at the end of execution.
     * @return A <code>String</code> text in the proper format to be stored
     * into the text file
     */
    @Override
    public String generateText() {
        return String.format("E # %d # %s # %s %s-%s", this.isDone ? 1 : 0, this.description, this.date,
                this.startTime.format(
                        DateTimeFormatter.ofPattern("HHmm")),
                this.endTime.format(
                        DateTimeFormatter.ofPattern("HHmm")));
    }

    /**
     * Returns the date, starting time and ending time of event in the format of
     * MMM-d-yyyy hh:mma-hh:mma.
     * @return the date, starting time and ending time of the event
     */
    public String getDateAndTime() {
        return "(at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + this.startTime.format(DateTimeFormatter.ofPattern("hh:mma")) + "-"
                        + this.endTime.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
    }

    /**
     * Changes the date and time of this event.
     * @param newDateAndTime the desired date and time range the event is to be rescheduled to
     * @return an event with the new date and time range
     * @throws DukeException if the user enters a single timing instead of a time range
     */
    public Event reschedule(String newDateAndTime) throws DukeException {
        String[] splitInputs = newDateAndTime.split(" "); //2021-02-03 1400-1500
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        String date = splitInputs[0];
        String timeRange = splitInputs[1];
        //if user did not enter a time range
        if (timeRange.indexOf("-") == -1) {
            throw new DukeException("Ooh lah lah! This is an event, please enter the time "
                    + "range of the event!");
        }
        String startTime = timeRange.split("-")[0];
        String endTime = timeRange.split("-")[1];
        LocalDate newDate = LocalDate.parse(date);
        LocalTime newStartTime = LocalTime.parse(startTime, formatter);
        LocalTime newEndTime = LocalTime.parse(endTime, formatter);
        this.date = newDate;
        this.startTime = newStartTime;
        this.endTime = newEndTime;
        return this;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[E][X] " + this.description + this.getDateAndTime();
        } else {
            return "[E][ ] " + this.description + this.getDateAndTime();
        }
    }
}
