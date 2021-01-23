package duke.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task{

    /** Timing of the event task */
    private LocalDate timing;

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

    @Override
    public String toString() {
        return "       " + this.type + super.toString().trim() + " " + getTiming();
    }
}
