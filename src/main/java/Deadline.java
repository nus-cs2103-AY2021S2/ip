import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task in the form of a deadline which has a date and time
 * that it must be done by
 */

public class Deadline extends Task {
    private final LocalDate dateBy; //deadline of task
    private final LocalTime timeBy;

    /**
     * Constructor takes in three parameters, <code>description</code> (description of deadline),
     * <code>dateBy</code> (date of deadline) and <code>timeBy</code> (time of deadline)
     * @param description a <code>String</code> that describes the deadline
     * @param dateBy a <code>LocalDate</code> specifying the date of deadline
     * @param timeBy a <code>LocalTime</code> specifying the time of deadline
     */

    public Deadline(String description, LocalDate dateBy, LocalTime timeBy) {
        super(description);
        this.dateBy = dateBy;
        this.timeBy = timeBy;
    }

    /**
     * A method that returns the date of the deadline
     * @return a <code>LocalDate</code> object that specifies the date of
     * the deadline
     */
    @Override
    public LocalDate getDate() {
        return this.dateBy;
    }

    /**
     * A method that generates the text to be stored into the text file
     * at the end of execution
     * @return A <code>String</code> text in the proper format to be stored
     * into the text file
     */
    @Override
    public String generateText() {
        return String.format("D # %d # %s # %s %s", this.isDone ? 1 : 0, this.description,
                this.dateBy,
                this.timeBy.format(DateTimeFormatter.ofPattern("HHmm")));
    }

    /**
     * A method that returns the date and time of deadline in the format of
     * MMM-d-yyyy hh:mma
     * @return a <code>String</code> of the date ond time of deadline
     */

    public String getDeadline() { //get deadline in format of String eg. (by: Sunday)
        return "(by: " + dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                timeBy.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[D][X] " + this.description + this.getDeadline();
        } else {
            return "[D][ ] " + this.description + this.getDeadline();
        }
    }
}
