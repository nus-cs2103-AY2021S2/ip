import org.json.simple.JSONObject;

/**
 * Represent a ToDo item, which is a child of Task
 */
public class ToDo extends Task {
    /**
     * Initialize a ToDo item
     * @param name The name of the item
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * toString method overriding the one in class Task
     * @return a user-friendly String representation of the ToDo item
     */
    @Override
    public String toString() {
        String doneMark = isDone? "X": " ";
        return String.format("[T][%s] %s", doneMark, name);
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "todo");
        jsonObject.put("isDone", isDone);
        jsonObject.put("description", name);
        jsonObject.put("time", "");
        return jsonObject;
    }
}
