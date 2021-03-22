package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Class representing a deadline task.
 */
public class Deadline extends Task {
    private final LocalDate deadLine;

    /**
     * Constructor for a deadline with description and a deadline.
     *
     * @param description
     * @param deadLine
     */
    public Deadline(String description, String deadLine) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
        this.deadLine = LocalDate.parse(deadLine.trim(), formatter);
    }


    @Override
    public String encode() {
        return "D|" + super.encode() + " | " + this.deadLine.format(DateTimeFormatter.ofPattern("yyyy-M-dd"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " | " + " (by: " + this.deadLine.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
