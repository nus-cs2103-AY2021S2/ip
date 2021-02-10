import java.util.ArrayList;

/**
 * Duke's TaskList. Contains the task list and has operations to modify the list.
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Class constructor. Creates the TaskList and an empty ArrayList of Tasks to store future tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds the specified next Task to the taskList.
     * @param nextTask The specified next Task.
     */
    public void add(Task nextTask) {
        this.tasks.add(nextTask);
    }

    /**
     * Gets the Task from the taskList at the index specified.
     * @param index The specified index of the wanted Task.
     * @return The Task in the taskList at the specified index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes and returns the Task from the taskList at the specified index.
     * @param index The specified index of the Task to be removed.
     * @return The Task that was removed from the taskList at the specified index.
     */
    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Returns the current size of the taskList.
     * @return Size of the taskList as an integer.
     */
    public int size() {
        return this.tasks.size();
    }
}
