import java.util.List;
import java.util.ArrayList;

/**
 * Store all the todos in a list DS
 */
public class Todo {
    private List<String> todos;

    /**
     * Initialize the todo list with an empty ArrayList
     */
    public Todo() {
        todos = new ArrayList<String>();
    }

    /**
     * Add the given item into the list
     * @param item The item to be added to the todo list
     */
    public void addItem(String item) {
        todos.add(item);
    }

    /**
     * Get the current todo list
     * @return The current todo list
     */
    public List<String> getTodos() {
        return todos;
    }

    public void printList() {
        for (int i = 1; i <= todos.size(); i++) {
            System.out.println(String.format("%d. %s", i, todos.get(i - 1)));
        }
    }
}