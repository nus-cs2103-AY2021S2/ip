package data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event extends Task {

    @JsonProperty
    protected LocalDate at;
    private String localDateOutputFormat = "MMM d yyyy";

    private Event() {}

    /**
     * Event (Task) constructor
     * @param description
     * @param at
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
            + " (at: " + at.format(DateTimeFormatter.ofPattern(localDateOutputFormat)) + ")";
    }
}
