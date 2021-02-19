package lihua.tasks;

import org.json.simple.JSONObject;

/**
 * Represent a lihua.tasks.ToDo item, which is a child of lihua.tasks.Task
 */
public class ToDo extends Task {
    /**
     * Initialize a lihua.tasks.ToDo item
     * @param name The name of the item
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * toString method overriding the one in class lihua.tasks.Task
     * @return a user-friendly String representation of the lihua.tasks.ToDo item
     */
    @Override
    public String toString() {
        String doneMark = isDone ? "X" : " ";
        return String.format("[T][%s] %s", doneMark, name);
    }

    /**
     * Wraps the todo object inside a json object to be stored in hard disk.
     *
     * @return The json object representing the todo object.
     */
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
