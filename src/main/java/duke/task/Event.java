package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents any event specified with event description, status and date and time.
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Constructor for Event class.
     * @param description Description of the event.
     * @param status To check if the event is done.
     * @param at Date and time of the event.
     */
    public Event(String description, int status, LocalDateTime at) {
        super(description,status);
        this.at = at;
    }

    /**
     * Converts event details into txt format for local storage.
     * @return The text stored in the local file.
     */
    @Override
    public String toTxt(){
        return "E " + super.toTxt() + " | " + at.format(DateTimeFormatter.ofPattern("HHmm, MMM dd yyyy")) + "\n";
    }

    /**
     * Generates event details displayed on the user interface.
     * @return Message output for Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("HHmm, MMM dd yyyy")) + ")";
    }
}
