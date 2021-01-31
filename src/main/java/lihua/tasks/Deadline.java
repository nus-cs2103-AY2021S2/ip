package lihua.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;

/**
 * Represents a lihua.tasks.Deadline item, which is a child of lihua.tasks.Task
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Initializes a lihua.tasks.Deadline item with a given end time
     *
     * @param name The name of the deadline
     * @param by The end time of the item, assumed to be 'yyyy-mm-dd'
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    /**
     * Gets the date of the deadline.
     *
     * @return The date of the deadline.
     */
    public LocalDate getDate() {
        return by;
    }

    /**
     * toString method overriding the one in class lihua.tasks.Task
     * @return a user-friendly String representation of the lihua.tasks.Deadline item
     */
    @Override
    public String toString() {
        String doneMark = isDone ? "X" : " ";
        return String.format("[D][%s] %s (by: %s)", doneMark, name,
                by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    /**
     * Wraps the deadline object inside a json object to be stored in hard disk.
     *
     * @return The json object representing the deadline object.
     */
    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("type", "deadline");
        jsonObject.put("isDone", isDone);
        jsonObject.put("description", name);
        jsonObject.put("time", by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return jsonObject;
    }
}
