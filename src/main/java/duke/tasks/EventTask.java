package duke.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * EventTask class models an actual task that the user is about to do or has completed
 * with a given timing of the task. It also inherits from the Task class.
 * Its details include the description
 */
public class EventTask extends Task {

    /** Timing of the event task */
    private LocalDate timing;
    /**
     * Constructor to initialize the EventTask
     *
     * @param description the description of the EventTask
     * @param timing the EventTask timing
     */
    public EventTask(String description, String timing) {
        super(description, "[E]");

        this.timing = LocalDate.parse(timing);
    }

    /**
     * Returns the timing of the event task formatted to be dd-MM-YYYY
     * and enclosed paranthesis.
     *
     * @return timing.
     */
    public String getTiming() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return "(at: " + this.timing.format(format) + ")";
    }

    /**
     * Returns the timing of the event task formatted to be dd-MM-YYYY.
     *
     * @return timing.
     */
    public String getUnformattedTiming() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return this.timing.format(format);
    }

    /**
     * Returns the timing of the event task as a LocalDate
     *
     * @return timing
     */
    public LocalDate getTimingAsLocalDate() {
        return this.timing;
    }
    /**
     * toString method of EventTask which prints out details of the EventTask
     */
    @Override
    public String toString() {
        return "       " + this.type + super.toString().trim() + " " + getTiming();
    }
}
