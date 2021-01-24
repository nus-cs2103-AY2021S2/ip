package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    /**
     * Initializes an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param taskIndex Index of the task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }


    /**
     * Returns a task from the list given its index.
     *
     * @param taskIndex Index of the task to be returned
     * @return Task request by user
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }


    /**
     * Returns if the task list is empty.
     *
     * @return If the task list is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return Total number of tasks in the list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns all tasks in the task list.
     *
     * @return List of tasks in the task list.
     */
    public List<Task> getAllTasks() {
        return tasks;
    }
}
