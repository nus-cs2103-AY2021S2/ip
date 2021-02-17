package tasks;

import static java.lang.Boolean.parseBoolean;

import datetime.KiwiDateTime;
import datetime.ParseDateTime;
import java.time.LocalDateTime;

/**
 * This class implements a type of task that users can add to their tasklist on this app.
 * The event task has an event-specific variable, event timing.
 */
public class Event extends Task {
    private KiwiDateTime eventTiming;

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
        return "E" + delimiter + description + delimiter + isDone
                + delimiter + eventTiming.unparse() + System.lineSeparator();
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
        assert oneLine.startsWith("E" + delimiter);

        // todo init num args per command
        // fixme realise how the order of args here depend on order of args in unparse function
        String[] args = oneLine.split(delimiter);
        assert args.length == 3 + 1 : // 3 + 1 bc command name, desc, done, time - much hardcoding
                "storage parser detecting fewer than needed event arguments";

        String desc = args[1];
        boolean isDone = parseBoolean(args[2]);
        KiwiDateTime dt = KiwiDateTime.parse(args[3]);

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
