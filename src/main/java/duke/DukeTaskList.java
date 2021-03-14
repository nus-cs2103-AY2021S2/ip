package duke;

import java.util.ArrayList;

import task.Task;

/**
 * Handles duke task list operations.
 */
public class DukeTaskList {
    /**
     * Contains the currently stored tasks.
     */
    protected ArrayList<Task> taskList;

    /**
     * Instantiates a new Duke task list.
     *
     * @param taskList the task list
     */
    DukeTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Add new task into task list.
     *
     * @param t the added task.
     */
    protected void add(Task t) {
        assert t != null : "Invalid task!";
        taskList.add(t);
    }

    /**
     * Remove a task from task list.
     *
     * @param i the index of the task to be removed.
     */
    protected void remove(int i) {
        assert i >= 0 && i < taskList.size() : "Index to be removed is invalid!";
        taskList.remove(i);
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return the size of the task list.
     */
    protected int size() {
        return taskList.size();
    }

    /**
     * Get task based on index.
     *
     * @param i the
     * @return the task at the given index.
     */
    protected Task get(int i) {
        assert i >= 0 && i < taskList.size() : "Index to be removed is invalid!";
        return taskList.get(i);
    }

    /**
     * Gets task list.
     *
     * @return the current task list.
     */
    protected ArrayList<Task> getTaskList() {
        assert this.taskList != null : "Error in task list!";
        return this.taskList;
    }

    @Override
    public String toString() {
        return taskList.toString();
    }
}
