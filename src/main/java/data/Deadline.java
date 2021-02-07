package data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Deadline extends Task {

    @JsonProperty
    protected LocalDate by;
    private String localDateOutputFormat = "MMM d yyyy";

    private Deadline() {
    }

    /**
     * Deadline constructor
     *
     * @param description
     * @param by
     */
    public Deadline(String description, TagList tags, LocalDate by) {
        super(description, tags);
        this.by = by;
    }

    /**
     * Returns a Deadline with status icon and 'by' date
     *
     * @return Deadline string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern(localDateOutputFormat))
                + ")";
    }
}
