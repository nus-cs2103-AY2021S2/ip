package fakebot.task;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TaskList class use to store task
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Class constructor specifying the task list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns task at index i.
     *
     * @param i index of the task.
     * @return a task.
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Adds task to task list.
     *
     * @param task to add to list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task index i.
     *
     * @param i Index of task to be removed.
     */
    public void removeTask(int i) {
        tasks.remove(i);
    }

    /**
     * Returns size of task list.
     *
     * @return the size of task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Finds all task that contain search string.
     *
     * @param search a string to be search.
     * @return a list of task that contain search string.
     */
    public List<Task> findTasks(String search) {
        return tasks.stream()
                .filter(t -> t.getTaskName().contains(search))
                .collect(Collectors.toList());
    }

    /**
     * Check if task list contain the same task.
     *
     * @param task a task to be check.
     * @return true if task exist
     */
    public boolean containTask(Task task) {
        return tasks.contains(task);
    }
}
