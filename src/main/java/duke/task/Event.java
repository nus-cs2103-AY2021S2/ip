package duke.task;

import duke.Parser;

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
        return "[E]" + super.toString() + String.format(" (at:%s)", Parser.parseDate(this.timeRange.strip()));
    }

    /**
     * String representation of the event when it is stored in a file.
     * @return file string representation of the event
     */
    @Override
    public String toFileString() {
        String string = "E|" + super.toFileString() + "|" + this.timeRange;
        return string;
    }
}
