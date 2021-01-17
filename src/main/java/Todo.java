import java.util.List;
import java.util.ArrayList;

/**
 * Store all the todos in a list DS
 */
public class Todo {
    private static List<String> todos = new ArrayList<>();

    /**
     * Add the given item into the list
     * @param item The item to be added to the todo list
     */
    public static void addItem(String item) {
        todos.add(item);
    }

    /**
     * Get the current todo list
     * @return The current todo list
     */
    public static List<String> getTodos() {
        return todos;
    }
}