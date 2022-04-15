package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task item with a deadline specified by a due date and possibly a
 * due time.
 *
 * @author Aaron Saw Min Sern
 */
public class Deadline extends Task {
    public final LocalDate date;
    public final LocalTime time;

    private Deadline(String description, LocalDate date) {
        super(false, description);
        this.date = date;
        this.time = null;
    }

    private Deadline(String description, LocalDate date, LocalTime time) {
        super(false, description);
        this.date = date;
        this.time = time;
    }

    /**
     * Factory constructor for class Deadline with only the due date.
     *
     * @param description the description of this Deadline instance
     * @param dateStr     the string representation of date with the form
     *                    "YYYY-MM-DD"
     * @return a Deadline instance with the description and the due date
     */
    public static Deadline create(String description, String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            return new Deadline(description, date);
        } catch (DateTimeParseException e) {
            System.out.println("\tPlease follow this format \"YYYY-MM-DD [hh:mm[:ss]]\" for datetime.");
            return null;
        }
    }

    /**
     * Factory constructor for class Deadline with both the due date and time.
     *
     * @param description the description of this Deadline instance
     * @param dateStr     the string representation of date with the form
     *                    "YYYY-MM-DD"
     * @param timeStr     the string representation of time with the form
     *                    "hh:mm:[ss]"
     * @return a deadline instance with the description, the due date and time
     */
    public static Deadline create(String description, String dateStr, String timeStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ISO_LOCAL_TIME);
            return new Deadline(description, date, time);
        } catch (DateTimeParseException e) {
            System.out.println("\tPlease follow this format \"YYYY-MM-DD [hh:mm[:ss]]\" for datetime.");
            return null;
        }
    }

    /**
     * Returns the string representation of the due date and time.
     *
     * @return the string representation of the due date and time
     */
    public String getDateTime() {
        if (time == null) {
            return date.toString();
        }
        return String.format("%s %s", this.date, this.time);
    }

    /**
     * Returns the string represenatation of this Deadline instance
     *
     * @return the string representation of this Deadline instance
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, this.getDateTime());
    }

    /**
     * Returns the encoded representation for this Deadline instance.
     *
     * @return the encoded representation of this Deadline instance
     */
    @Override
    public String encode() {
        return String.format("D | %s | %s | %s", this.isDone ? "1" : "0", this.description, this.getDateTime());
    }

    /**
     * Returns true if two Deadline instances are equivalent.
     *
     * @param o the other object to be compared
     * @return true if two Deadline instances are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deadline)) {
            return false;
        }
        Deadline other = (Deadline) o;
        return this.description.equals(other.description) && this.isDone == other.isDone && this.date.equals(other.date)
                && (this.time == null || this.time.equals(other.time));
    }

    /**
     * Returns the hashcode of this Deadline instance.
     *
     * @return hashcode of this Deadline instance
     */
    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + (isDone ? 1 : 0);
        return result;
    }
}
