package duke.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of {@code Task}
 */
public class TaskList {
    private final List<Task> lst;

    /**
     * Creates a {@code TaskList} object with an empty task list component.
     */
    public TaskList() {
        lst = new ArrayList<>();
    }

    /**
     * Adds a task to the list of tasks.
     * @param task Task to be added.
     */
    public void addItem(Task task) {
        lst.add(task);
    }

    /**
     * Removes a task from the list of tasks.
     * @param id Index of task to be removed.
     */
    public void removeItem(int id) {
        lst.remove(id);
    }

    /**
     * Marks a task as done.
     * @param id Index of task to be marked as done.
     */
    public void doneTask(int id) {
        lst.get(id).markAsDone();
    }

    /**
     * Returns list of tasks.
     * @return List of tasks.
     */
    public List<Task> getLst() {
        return lst;
    }

    /**
     * Checks if tasks in task list contains the keyword.
     * @param keyword Keyword to be searched.
     * @return List of Tasks containing the keyword.
     */
    public List<Task> tasksContainingKeyword(String keyword) {
        List<Task> tasksWithKeyword = new ArrayList<>();
        lst.stream().filter(task -> task.contains(keyword)).forEach(task -> tasksWithKeyword.add(task));
        return tasksWithKeyword;
    }

    /**
     * Returns size of task list.
     * @return Size of task list.
     */
    public int size() {
        return lst.size();
    }
}
