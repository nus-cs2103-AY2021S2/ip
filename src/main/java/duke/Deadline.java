package duke;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Deadline extends Task {

    @JsonProperty
    protected String by;

    private Deadline() {}

    /**
     * Deadline constructor
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
