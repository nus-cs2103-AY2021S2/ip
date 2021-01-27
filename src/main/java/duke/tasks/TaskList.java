package duke.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of {@code Task}
 */
public class TaskList {
    private final List<Task> lst;

    public TaskList() {
        lst = new ArrayList<>();
    }

    /**
     * Adds a task to the list of tasks.
     * @param task task to be added
     */
    public void addItem(Task task) {
        lst.add(task);
    }

    /**
     * Removes a task from the list of tasks.
     * @param id index of task to be removed
     */
    public void removeItem(int id) {
        lst.remove(id);
    }

    /**
     * Marks a task as done.
     * @param id
     */
    public void doneTask(int id) {
        lst.get(id).markAsDone();
    }

    /**
     * Returns list of tasks.
     * @return list of tasks
     */
    public List<Task> getLst() {
        return lst;
    }

    public int size() {
        return lst.size();
    }
}
