package ip.src.main.java;
import java.util.List;
import java.util.ArrayList;

/**
 * TaskList stores and manipulate the tasks in a List.
 *
 */

public class TaskList {
    protected List<Task> list;

    TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a Task to the list.
     *
     * @param task New Task to be added to list.
     */

    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Checks if list is empty.
     *
     * @return Boolean to indicate if list is empty.
     */

    public boolean Empty() {
        return this.list.isEmpty();
    }

    /**
     * Prints the tasks in the list in order.
     *
     */

    public void printTasks() {
        int counter = 1;
        for (Task element : this.list) {
            System.out.println(String.valueOf(counter) + ". " + element);
            counter++;
        }
    }

    /**
     * Gets the task at a specified position in the list.
     *
     * @param id The position of the task to be retrieved in the list.
     * @return A task in the list.
     */

    public Task getTask(int id) {
        return this.list.get(id);
    }

    /**
     * Removes a task at a specified position in the list.
     *
     * @param id The position of the task to be removed from the list.
     */

    public void remove(int id) {
        this.list.remove(id);
    }

    /**
     * Returns size of the Task list.
     *
     * @return The number of tasks in the list.
     */

    public int size(){
        return this.list.size();
    }
}
