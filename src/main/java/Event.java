import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task in the form of an event which has a date,
 * an event starting time and an event ending time
 */

public class Event extends Task {
    private final LocalDate date;
    private final LocalTime startTime; //in 24h format
    private final LocalTime endTime;

    /**
     * Constructor takes in four parameters <code>description</code>,
     * <code>date</code>, <code>startTime</code> and <code>endTime</code>
     * @param description the description of the event in the form of a <code>String</code>
     * @param date the date of the event in the form of a <code>LocalDate</code>
     * @param startTime the starting time of the event in the form of a <code>LocalTime</code>
     * @param endTime the ending time of the event in the form of a <code>LocalTime</code>
     */
    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * A method returning the date of the event
     * @return a <code>LocalDate</code> object that specifies
     * the date of the event
     */

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * A method that generates the text to be stored into the text file
     * at the end of execution
     * @return A <code>String</code> text in the proper format to be stored
     * into the text file
     */
    @Override
    public String generateText() {
        return String.format("E # %d # %s # %s %s-%s", this.isDone ? 1 : 0, this.description, this.date,
                this.startTime.format(DateTimeFormatter.ofPattern("HHmm")),
                this.endTime.format(DateTimeFormatter.ofPattern("HHmm")));
    }

    /**
     * A method that returns the date, starting time and ending time of the event
     * in the format of MMM-d-yyyy hh:mma-hh:mma
     * @return a <code>String</code> of the date, starting time and ending time
     * of the event
     */
    public String getTime() {
        return "(at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                this.startTime.format(DateTimeFormatter.ofPattern("hh:mma")) + "-" +
                this.endTime.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[E][X] " + this.description + this.getTime();
        } else {
            return "[E][ ] " + this.description + this.getTime();
        }
    }
}
