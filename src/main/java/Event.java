import org.json.simple.JSONObject;

/**
 * Represent an Event item, which is a child of Task
 */
public class Event extends Task {
    private String period;

    /**
     * Initialize an Event with a time period
     * @param name The name of the event
     * @param period The time period of the event
     */
    public Event(String name, String period) {
        super(name);
        this.period = period;
    }

    /**
     * toString method overriding the one in class Task
     * @return a user-friendly String representation of the Event item
     */
    @Override
    public String toString() {
        String doneMark = isDone? "X": " ";
        return String.format("[E][%s] %s (at: %s)", doneMark, name, period);
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "event");
        jsonObject.put("isDone", isDone);
        jsonObject.put("description", name);
        jsonObject.put("time", period);
        return jsonObject;
    }
}