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

    // created trying to bug fix
    public Event(String desc, KiwiDateTime eventTiming) {
        super(desc);
        this.eventTiming = eventTiming;
    }

    // used when parsing event task from file
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
        // should abstract e here away
        // todo use join instead for all tasks, can standardize
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
        // some repetition in this function across all types of tasks but abstracting them might be costly
        // another assumption: there's no line separator (storage scanner removes line separator that unparse adds)
        assert oneLine.startsWith("E" + STORAGE_DELIMITER);

        // todo init num fields per command
        // fixme realise how the order of fields here depend on order of fields in unparse function
        // split string into different fields
        String[] fields = oneLine.split(STORAGE_DELIMITER);
        assert fields.length == 3 + 1 : // 3 + 1 bc command name, desc, done, time - much hardcoding
                "storage parser detecting fewer than needed event arguments";

        // parse strings into fields needed for this class
        String desc = fields[1];
        boolean isDone = parseBoolean(fields[2]);
        KiwiDateTime dt = KiwiDateTime.parse(fields[3]);

        return new Event(desc, dt, isDone);
    }

    /**
     * This method only exists for one-off testing of this class.
     * @param args
     */
    public static void main(String[] args) {
//        Event t = new Event("hello world", "30-01 8PM");
//        System.out.println(t);
//        t.markAsDone();
//        System.out.println(t);
//        System.out.println(t.unparse());
//        System.out.println(System.lineSeparator().getBytes());
//        System.out.println(parse("E;;parsing;;false;;03-03 6PM")); // lineSeperator needs to be removed from unparse
//        // test with other cases without relying on t
    }
}
