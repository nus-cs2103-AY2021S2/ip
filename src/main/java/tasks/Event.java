package tasks;

import static java.lang.Boolean.parseBoolean;

import datetime.ParseDateTime;
import java.time.LocalDateTime; // todo wrap localdate time in parsedatetime

/**
 * This class implements a type of task that users can add to their tasklist on this app.
 * The event task has an event-specific variable, event timing.
 */
public class Event extends Task {
    private LocalDateTime eventTiming; // todo make a custom class for datetimes (it can contain formatting functions)

    /**
     * Public constructor which is used when parsing user inputted command
     * "event {desc} /at {timing}".
     * @param desc
     * @param eventTiming
     */
    public Event(String desc, String eventTiming) {
        super(desc);
        this.eventTiming = ParseDateTime.parse(eventTiming);
    }

    // created trying to bug fix
    public Event(String desc, LocalDateTime eventTiming) {
        super(desc);
        this.eventTiming = eventTiming;
    }

    // used when parsing event task from file
    private Event(String desc, String eventTiming, boolean isDone) {
        super(desc, isDone);
        this.eventTiming = ParseDateTime.parse(eventTiming);
    }

    /**
     * Returns a string representation of an event task
     * @return String representation of an event
     */
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description
                + " (at: " + ParseDateTime.readableString(eventTiming) + ")";
    }

    @Override
    public String unparse() {
        // should abstract e here away
        return "E" + delimiter + description + delimiter + isDone
                + delimiter + ParseDateTime.unparse(eventTiming) + System.lineSeparator();
    }

    /**
     * Creates a deadline object based on the string stored in the hard disk.
     * Example stored string for this class: "//E;;desc;;true;;timing".
     * @param oneLine One line of stored input to be parsed into a deadline
     * @return tasks.Deadline Object
     */
    public static Event parse(String oneLine) {
        // some repetition in this function across all types of tasks but abstracting them might be costly
        assert oneLine.startsWith("T" + delimiter);

        int descStartIdx = oneLine.indexOf(delimiter);
        int descEndIdx = oneLine.indexOf(delimiter, descStartIdx + 1);
        String desc = oneLine.substring(descStartIdx + delimiter.length(), descEndIdx);

        int doneEndIdx = oneLine.indexOf(delimiter, descEndIdx + 1);
        String doneStr = oneLine.substring(descEndIdx + delimiter.length(), doneEndIdx);
        Boolean isDone = parseBoolean(doneStr);

        String eventTiming = oneLine.substring(doneEndIdx + delimiter.length());

        return new Event(desc, eventTiming, isDone);
    }

    /**
     * This method only exists for one-off testing of this class.
     * @param args
     */
    public static void main(String[] args) {
        Event t = new Event("hello world", "8pm");
        System.out.println(t);
        t.markAsDone();
        System.out.println(t);
        System.out.println(t.unparse());
        System.out.println(parse(t.unparse()));
        // test with other cases without relying on t
    }
}
