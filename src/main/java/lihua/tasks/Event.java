package lihua.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;

/**
 * Represent an lihua.tasks.Event item, which is a child of lihua.tasks.Task
 */
public class Event extends Task {
    /** The date of the event */
    private final LocalDate period;

    /**
     * Initialize an lihua.tasks.Event with a time period
     * @param name The name of the event
     * @param period The date of the event, assumed to be 'yyyy-mm-dd'
     */
    public Event(String name, LocalDate period) {
        super(name);
        this.period = period;
    }

    /**
     * Gets the date of the event.
     *
     * @return The date of the event.
     */
    public LocalDate getDate() {
        return period;
    }

    /**
     * toString method overriding the one in class lihua.tasks.Task
     * @return a user-friendly String representation of the lihua.tasks.Event item
     */
    @Override
    public String toString() {
        String doneMark = isDone ? "X" : " ";
        return String.format("[E][%s] %s (at: %s)", doneMark, name,
                period.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    /**
     * Wraps the event object inside a json object to be stored in hard disk.
     *
     * @return The json obejct representing the deadline object.
     */
    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("type", "event");
        jsonObject.put("isDone", isDone);
        jsonObject.put("description", name);
        jsonObject.put("time", period.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return jsonObject;
    }
}
