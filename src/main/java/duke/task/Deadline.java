package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline specified in the "by" argument.
 * If deadline is parsable as a LocalDateTime or LocalDate, it will be stored in the variable byDateTime or byDate.
 */
public class Deadline extends Task {
    protected String inputAfterBy;
    protected LocalDateTime byDateTime;
    protected LocalDate byDate;

    /**
     * Creates a task with a deadline specified in the "by" argument.
     * Tries to parse deadline in various DateTimeFormatter patterns and stores as LocalDateTime object in byDateTime,
     * or store as LocalDate object in byDate.
     *
     * @param description  Describes the task.
     * @param inputAfterBy Specifies the deadline of this task.
     */
    public Deadline(String description, String inputAfterBy) {
        super(description);
        this.inputAfterBy = inputAfterBy;

        String[] dateTimePatterns = {"yyyy-MM-dd kkmm", "dd/MM/yyyy kkmm", "dd-MM-yyyy kkmm"};
        String[] datePatterns = {"dd-MM-yyyy", "dd/MM/yyyy"};

        // Try parsing inputAfterBy as LocalDateTime
        for (String dateTimePattern : dateTimePatterns) {
            try {
                byDateTime = LocalDateTime.parse(inputAfterBy, DateTimeFormatter.ofPattern(dateTimePattern));
            } catch (DateTimeParseException e) {
                System.out.println("Input after \"/by\" not of " + dateTimePattern + " format.");
            }
        }

        // Try parsing inputAfterBy as LocalDate
        for (String datePattern : datePatterns) {
            try {
                byDate = LocalDate.parse(inputAfterBy, DateTimeFormatter.ofPattern(datePattern));
            } catch (DateTimeParseException e) {
                System.out.println("Input after \"/by\" not of " + datePattern + " format.");
            }
        }
    }

    @Override
    public String toString() {
        if (byDateTime == null && byDate == null) {
            return "[D]" + super.toString() + " (by: " + this.inputAfterBy + ")";
        } else if (byDateTime != null) {
            return "[D]" + super.toString()
                    + " (by: " + byDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, ha")) + ")";
        } else {
            // byDate is not null
            return "[D]" + super.toString()
                    + " (by: " + byDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
        }
    }
}
