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
     * Get the given item done, print out the result for reference
     * @param index 1-based index number referring to the items in the list
     */
    public void getItemDone(int index) {
        try {
            todos.get(index - 1).setDone(true);
            System.out.println("Nice! I have marked this item as done: ");
            System.out.print("----");
            System.out.println(todos.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, the item id you specified is not valid. " +
                    "Try enter \'list\' to see the range of id you can enter.");
        }
    }

    /**
     * Print out the todo list in a user-friendly format
     */
    public void printList() {
        System.out.println("These are the tasks in your list so far:");
        for (int i = 1; i <= todos.size(); i++) {
            System.out.println(String.format("%d.%s", i, todos.get(i - 1)));
        }
    }
}