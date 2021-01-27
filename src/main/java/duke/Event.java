package duke;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event extends Task {

    @JsonProperty
    protected String at;

    private Event() {}

    /**
     * Event (Task) constructor
     * @param description
     * @param at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
