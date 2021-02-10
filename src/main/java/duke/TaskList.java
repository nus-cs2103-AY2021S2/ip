package duke;

import java.util.ArrayList;

import duke.task.Task;


/**
 * Representation of the TaskList used by Duke stored in the form of an ArrayList.
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the Task located at the specified index.
     *
     * @param index Index of Task to be retrieved.
     * @return Task at the specified index.
     */
    public Task get(int index) {
        assert index >= 0;
        return taskList.get(index);
    }

    /**
     * Adds the given Task to the end of the list.
     *
     * @param task Task to be added.
     * @return true if operation was successful, false otherwise.
     */
    public boolean add(Task task) {
        return taskList.add(task);
    }

    /**
     * Removes the Task located at the given index.
     *
     * @param index Index of Task to be removed.
     * @return the Task that was removed.
     */
    public Task remove(int index) {
        assert index >= 0;
        return taskList.remove(index);
    }

    /**
     * Returns the size (number of elements) in the TaskList.
     *
     * @return  size of the TaskList.
     */
    public int size() {
        return taskList.size();
    }
}
