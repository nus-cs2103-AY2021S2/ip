package duke.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import duke.exception.DukeException;
import duke.util.Utils;

/**
 * Represents a list of tasks.
 */
public class TaskList implements Iterable<Task> {
    private List<Task> tasks;

    /**
     * Constructor. Defaults to an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor takes in a list of tasks.
     * @param tasks The list of tasks.
     * @throws DukeException Error if the list of tasks contains any duplicates.
     */
    public TaskList(List<Task> tasks) throws DukeException {
        if (!Utils.elementsAreUnique(tasks)) {
            throw new DukeException("Tasks contains a duplicate!");
        }
        this.tasks = tasks;
    }

    /**
     * Returns a task from the list of tasks
     * using a 0-based index.
     * @param index The 0-based index of the task.
     * @return The task at the 0-based index of the list of tasks.
     */
    public Task get(int index) {
        assert tasks != null;
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list of tasks.
     * @return The number of tasks in the list of tasks.
     */
    public int size() {
        assert tasks != null;
        return this.tasks.size();
    }

    /**
     * Adds a task to the list of tasks.
     * @param task The task to be added.
     * @throws DukeException Error if the task to be added is a duplicate.
     */
    public void add(Task task) throws DukeException {
        assert tasks != null;
        if (contains(task)) {
            throw new DukeException("Cannot add duplicate task!");
        }
        this.tasks.add(task);
    }

    /**
     * Removes a task from the list of tasks using a 0-based index.
     * @param index The 0-based index of the task.
     * @return The task which was removed.
     */
    public Task remove(int index) {
        assert tasks != null;
        return this.tasks.remove(index);
    }

    /**
     * Reference from github.com/se-edu/addressbook-level2
     * Checks if the list contains an equivalent task as the given argument.
     * The {@link Task#isSameTask} method is used for this comparison, which
     * defines a weaker notion of equality.
     */
    public boolean contains(Task toCheck) {
        for (Task task : tasks) {
            if (task.isSameTask(toCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an iterator over the list of tasks.
     * @return The iterator over the list of tasks in proper sequence.
     */
    @Override
    public Iterator<Task> iterator() {
        assert tasks != null;
        return tasks.iterator();
    }

    /**
     * Returns a list of tasks that contains the keywords.
     * @param keywords The keywords to match.
     * @return The list of tasks that contain the keywords.
     * @throws DukeException Error if the list of tasks contains any duplicates.
     */
    public TaskList filter(String keywords) throws DukeException {
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
            tasksString.append(String.format("%d.%s\n", count, task));
            count++;
        }
        return tasksString.toString();
    }
}
