package main.java.subfiles;

/**
 * The Event class represents a single event created by the
 * user via user input to the Duke program. It contains
 * functions which enable the user to mark the task as done,
 * and a date which the event is held on.
 *
 * @author  arsatis
 * @version 1.0
 * @since   2021-01-19
 */
public class Event extends Task {
    /** Date which the event is held on */
    private String date;

    public Event(String s, String t) {
        super(s);
        date = t;
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + name + " (at: " + date + ")";
    }

}
