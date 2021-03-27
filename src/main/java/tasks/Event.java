package tasks;

import static java.lang.Boolean.parseBoolean;

import datetime.KiwiDateTime;

/**
 * This class implements a type of task that users can add to their tasklist on this app.
 * The event task has an event-specific variable, event timing.
 */
public class Event extends Task {
    private final KiwiDateTime eventTiming;

    /**
     * Public constructor which is used when parsing user inputted command
     * "event {desc} /at {timing}".
     * @param desc
     * @param eventTiming
     */
    public Event(String desc, KiwiDateTime eventTiming) {
        super(desc);
        this.eventTiming = eventTiming;
    }

    // used when parsing event task from stored file
    private Event(String desc, KiwiDateTime eventTiming, boolean isDone) {
        super(desc, isDone);
        this.eventTiming = (eventTiming);
    }

    /**
     * Returns a string representation of an event task
     * @return String representation of an event
     */
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description
                + " (at: " + eventTiming + ")";
    }

    @Override
    public String unparse() {
        return "E" + STORAGE_DELIMITER + description + STORAGE_DELIMITER + isDone
                + STORAGE_DELIMITER + eventTiming.unparse() + System.lineSeparator();
    }

    /**
     * Creates an event object based on the string stored in the hard disk.
     * Example stored string for this class: "//E;;desc;;true;;timing".
     * @param oneLine One line of stored input to be parsed into a deadline
     * @return tasks.Event Object
     */
    public static Event parse(String oneLine) {
        assert oneLine.startsWith("E" + STORAGE_DELIMITER);

        String[] fields = oneLine.split(STORAGE_DELIMITER);
        assert fields.length == 3 + 1 : // 3 + 1 bc command name, desc, done, time - much hardcoding
                "storage parser detecting fewer than needed event arguments";

        String desc = fields[1];
        boolean isDone = parseBoolean(fields[2]);
        KiwiDateTime dt = KiwiDateTime.parse(fields[3]);

        return new Event(desc, dt, isDone);
    }
}
