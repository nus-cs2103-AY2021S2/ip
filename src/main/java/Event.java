import java.time.LocalDateTime;

/** Class Event that represents a Event task**/
public class Event extends Task {
    /** Symbol to represent a Event task **/
    private static final char SYMBOL = 'E';
    /** Start datetime of the event **/
    private LocalDateTime start;
    /** End datetime of the event **/
    private LocalDateTime end;

    /**
     * Constructor of a Event task.
     * @param desc Description of the deadline task
     * @param start Start datetime of the event
     * @param end End datetime of the event
     */
    public Event(String desc, LocalDateTime start, LocalDateTime end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the representation of a task.
     * @return String representation of a task
     */
    @Override
    public String toString() {
        return String.format("[%c] %s (%s - %s)", SYMBOL, super.toString(),
                    super.format(this.start), super.format(this.end));
    }

    /**
     * Returns the representation of a task for saving.
     * @return String representation of a task for saving
     */
    @Override
    public String save() {
        return String.format("%c,%s,%s,%s\n", SYMBOL, super.save(),
                    super.saveFormat(this.start), super.saveFormat(this.end));
    }
}
