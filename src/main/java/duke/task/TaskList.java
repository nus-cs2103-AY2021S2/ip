package duke.task;

import java.util.ArrayList;

/**
 * A class represents a TaskList.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructs a TaskList.
     * @param tasks An ArrayList of tasks to be stored in this TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    /**
     * Adds the task into this TaskList.
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task according to the index from the TaskList.
     * @param index The index of the task to be removed.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Returns the size of the TaskList.
     * @return The size of the TaskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns a task according to the index in the TaskList.
     * @param index The index of the task.
     * @return The task.
     */
    public Task get(int index) {
        return taskList.get(index);
    }
}
