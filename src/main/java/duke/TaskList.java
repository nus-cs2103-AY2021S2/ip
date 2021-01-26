package duke;

import java.util.ArrayList;

/**
 * Represents a list of Task objects, and governs interactions between Tasks and this List.
 */
public class TaskList {
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
        return tasks.remove(idx - 1); // TaskList is 1-indexed
    }

    /**
     * Returns the Task at the specified index from the TaskList.
     * @param idx (1-indexed) index of the Task to be returned from the TaskList.
     * @return Task to at the given (1-indexed) index of the TaskList.
     */
    public Task get(int idx) {
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
     * Removes all Tasks from the TaskList.
     */
    public void clear() {
        tasks.clear();
    }

    public TaskList find(String search) {
        TaskList matchingTasks = new TaskList();
        String searchPattern = "^.*" + search.toLowerCase() + ".*$";
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().matches(searchPattern)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
