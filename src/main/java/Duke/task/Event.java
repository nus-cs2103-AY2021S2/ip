package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event.
 * Sub-class of Task.
 */
public class Event extends Task {
    protected LocalDateTime time;

    /**
     * Returns an Event.
     *
     * @param msg description of Event.
     * @param time time of Event.
     * @return Event
     */
    public Event(String msg, LocalDateTime time) {
        super(msg);
        this.time = time;
    }

    /**
     * Returns an Event.
     *
     * @param msg description of Event.
     * @param isDone boolean that tracks whether Event is completed.
     * @param time time of Event.
     * @return Event
     */
    Event(String msg, Boolean isDone, LocalDateTime time) {
        super(msg, isDone);
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Compare Events.
     *
     * @param task
     * @return
     */
    @Override
    public boolean equals(Task task) {
        if (task instanceof Event) {
            Event event = (Event) task;
            boolean a = this.isDone.equals(event.getDone());
            boolean b = this.msg.equals(event.getMsg());
            boolean c = this.getTime().equals(event.getTime());
            return a && b && c;
        } else {
            return false;
        }
    }


    /**
     * Returns an Event that set boolean isDone as true.
     *
     * @return Event marked as done.
     */
    @Override
    public Event setDone() {
        System.out.println("event set done");
        return new Event(this.msg, true, this.time);
    }

    /**
     * Encodes Task to string format for storage.
     *
     * @return String
     */
    @Override
    public String encode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "E" + "|" + super.encode() + "|" + time.format(formatter);
    }

    /**
     * Returns a String that describes Event.
     *
     * @return String
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return "[E]" + super.toString() + "(at: " + time.format(formatter) + ")";
    }
}
