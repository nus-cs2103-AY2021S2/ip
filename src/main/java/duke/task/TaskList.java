package duke.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The TaskList class represents the
 * abstraction of a list of tasks.
 */
public class TaskList implements Iterable<Task> {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList with a certain list of tasks.
     * @param tasks The specified list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves a task from the TaskList with a 0-based index.
     * @param index The specified index.
     * @return The task at the specified index in the TaskList.
     */
    public Task get(int index) {
        assert tasks != null;
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     * @return The number of tasks.
     */
    public int size() {
        assert tasks != null;
        return this.tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     * @param task The specified task.
     */
    public void add(Task task) {
        assert tasks != null;
        this.tasks.add(task);
    }

    /**
     * Removes a task from the TaskList using a 0-based index
     * @param index The specified index.
     * @return The task which was removed.
     */
    public Task remove(int index) {
        assert tasks != null;
        return this.tasks.remove(index);
    }

    /**
     * Returns an iterator over the tasks in the TaskList.
     * @return The iterator over the tasks in the TaskList in proper sequence.
     */
    @Override
    public Iterator<Task> iterator() {
        assert tasks != null;
        return tasks.iterator();
    }

    public TaskList filter(String keywords) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(keywords)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }

    /**
     * Returns a string representation of the tasks in the TaskList.
     * @return The string representation of the tasks in the TaskList.
     */
    @Override
    public String toString() {
        assert tasks != null;
        StringBuilder tasksString = new StringBuilder();
        int count = 1;
        for (Task task : tasks) {
            tasksString.append(String.format("%d.%s", count, task));
        }
        return tasksString.toString();
    }
}
