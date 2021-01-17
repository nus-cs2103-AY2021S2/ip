import java.util.List;
import java.util.ArrayList;

/**
 * Store all the todos in a list DS
 */
public class Todos {
    private List<Item> todos;

    /**
     * Initialize the todo list with an empty ArrayList
     */
    public Todos() {
        todos = new ArrayList<Item>();
    }

    /**
     * Add the given item into the list
     * @param item The item to be added to the todo list
     */
    public void addItem(String item) {
        todos.add(new Item(item));
    }

    /**
     * Get the current todo list
     * @return The current todo list
     */
    public List<Item> getTodos() {
        return todos;
    }

    /**
     * Print out the todo list in a user-friendly format
     */
    public void printList() {
        printIntro();
        for (int i = 1; i <= todos.size(); i++) {
            String doneMark = todos.get(i - 1).isDone()? "X": " ";
            System.out.println(String.format("%d.[%s] %s", i, doneMark, todos.get(i - 1)));
        }
    }

    private void printIntro() {
        System.out.println("These are the tasks in your list so far:");
    }
}