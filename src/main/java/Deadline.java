import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task in the form of a deadline which has a date and time
 * that it must be done by
 */

public class Deadline extends Task {
    private LocalDate dateBy;
    private LocalTime timeBy;

    /**
     * Constructor takes in three parameters, <code>description</code>,
     * <code>dateBy</code> and <code>timeBy</code>.
     * @param description a description of the deadline
     * @param dateBy the date of deadline
     * @param timeBy the time of deadline
     */
    public Deadline(String description, LocalDate dateBy, LocalTime timeBy) {
        super(description);
        this.dateBy = dateBy;
        this.timeBy = timeBy;
    }

    /**
     * Returns the date of the deadline
     * @return the date of the deadline
     */
    @Override
    public LocalDate getDate() {
        return this.dateBy;
    }

    /**
     * Generates the text of this task in the proper format to be stored
     * into the text file at the end of execution.
     * @return A <code>String</code> text in the proper format to be stored
     * into the text file
     */
    @Override
    public String generateText() {
        return String.format("D # %d # %s # %s %s",
                this.isDone ? 1 : 0,
                        this.description, this.dateBy,
                                this.timeBy.format(
                                        DateTimeFormatter.ofPattern("HHmm")));
    }

    /**
     * Returns the date and time of deadline in the format of
     * MMM-d-yyyy hh:mma
     * @return a <code>String</code> of the date ond time of deadline
     */
    public String getDeadline() {
        return "(by: " + dateBy.format(
                DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                        + timeBy.format(
                                DateTimeFormatter.ofPattern("hh:mma")) + ")";
    }

    /**
     * Changes the date and time of this deadline.
     * @param newDateAndTime the desired date and time the deadline is to be rescheduled to
     * @return a deadline with the new date and timing
     * @throws DukeException if the user enters a time range instead of a single deadline timing
     */

    public Deadline reschedule(String newDateAndTime) throws DukeException {
        String[] splitInputs = newDateAndTime.split(" "); //2021-02-03 1400
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        String date = splitInputs[0];
        String time = splitInputs[1];
        //if user input contains a time range
        if (time.indexOf("-") != -1) {
            throw new DukeException("Ooh lah lah! This is a deadline, enter only the timing"
                    + " of the deadline!");
        }
        LocalDate newDateBy = LocalDate.parse(date);
        LocalTime newTimeBy = LocalTime.parse(time, formatter);
        this.dateBy = newDateBy;
        this.timeBy = newTimeBy;
        return this;
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
