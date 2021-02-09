import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate time;

    /**
     * Initialises a newly created Deadline object
     * so that it represents a Deadline task
     * with the name and time according to its supplied arguments.
     * Initialises the Deadline task isDone status to false.
     *
     * @param name the description of the Deadline task.
     * @param time LocalDate object that represents
     *            the time this Deadline is happening.
     */
    public Deadline(String name, String time, String priority) {
        super(name, priority);
        this.time = LocalDate.parse(time);
    }

    /**
     * Initialises a newly created Deadline object
     * so that it represents a Deadline task
     * with the name, isDone and time according to its supplied arguments.
     *
     * @param name the description of the Deadline task.
     * @param isDone the status of the Deadline task.
     * @param time LocalDate object that represents
     *            the time this Deadline is happening.
     */
    public Deadline(String name, boolean isDone, String time, String priority) {
        super(name, isDone, priority);
        this.time = LocalDate.parse(time);
    }

    /**
     * Creates a String representation of Deadline object.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                time.format(DateTimeFormatter.ofPattern("dd MMM yyy")));
    }

    LocalDate getTime() {
        return this.time;
    }
}
