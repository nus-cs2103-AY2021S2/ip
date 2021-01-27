package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Deadline extends Task {

    @JsonProperty
    protected LocalDate by;
    private String localDateOutputFormat = "MMM d yyyy";

    private Deadline() {}

    /**
     * Deadline constructor
     * @param description
     * @param by
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
            + " (by: " + by.format(DateTimeFormatter.ofPattern(localDateOutputFormat)) + ")";
    }
}
