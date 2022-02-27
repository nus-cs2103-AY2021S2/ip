package duke;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a list of Task objects, and governs interactions between Tasks and this List.
 */
public class TaskList {
    private static final String REGEX_PATTERN_START = "^.*";
    private static final String REGEX_PATTERN_END = ".*$";

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a Task to the TaskList.
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the Task at the specified index from the TaskList.
     * @param idx (1-indexed) index of the Task to be removed from the TaskList.
     * @return Task that was removed from the TaskList.
     */
    public Task remove(int idx) {
        assert (idx > 0 && idx <= tasks.size()) : "Task index out of bounds";
        return tasks.remove(idx - 1); // TaskList is 1-indexed
    }

    /**
     * Returns the Task at the specified index from the TaskList.
     * @param idx (1-indexed) index of the Task to be returned from the TaskList.
     * @return Task to at the given (1-indexed) index of the TaskList.
     */
    public Task get(int idx) {
        assert (idx > 0 && idx <= tasks.size()) : "Task index out of bounds";
        return tasks.get(idx - 1); // TaskList is 1-indexed
    }

    /**
     * Returns the number of Tasks in the TaskList.
     * @return Number of Tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Reverses the order of the Tasks in the TaskList.
     */
    public void reverse() {
        Collections.reverse(tasks);
    }

    /**
     * Finds all Tasks whose descriptions contain the search parameter and returns them in a TaskList.
     * @param search Search parameter, as a String.
     * @return TaskList containing all the matching Tasks.
     */
    public TaskList find(String search) {
        TaskList matchingTasks = new TaskList();
        String searchPattern = REGEX_PATTERN_START + search.toLowerCase() + REGEX_PATTERN_END;
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().matches(searchPattern)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
