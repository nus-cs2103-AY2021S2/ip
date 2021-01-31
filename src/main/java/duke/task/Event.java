package duke.task;

/**
 * Class containing the relevant information about an Event.
 */
public class Event extends Task {
    private String timeRange;

    /**
     * Constructor method for Event.
     * @param content description of event
     * @param timeRange the time when the event starts
     */
    public Event(String content, String timeRange) {
        super(content);
        this.timeRange = timeRange;
    }

    /**
     * String representation of the event.
     * @return string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at:%s)", this.parseDate(this.timeRange.strip()));
    }

    /**
     * String representation of the event when it is stored
     * in a file.
     * @return file string representation of the event
     */
    @Override
    public String toFileString() {
        String done;
        if (this.getDone()) {
            done = "1";
        } else {
            done = "0";
        }
        String string = "E|" + done + "|" + this.getDesc() + "|" + this.timeRange;
        return string;
    }
}
