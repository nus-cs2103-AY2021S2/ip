package duke;

import task.Task;

import java.util.ArrayList;

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
     * @param t the t
     */
    protected void add(Task t) {
        taskList.add(t);
    }

    /**
     * Remove a task from task list.
     *
     * @param i the
     */
    protected void remove(int i) {
        taskList.remove(i);
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return the int
     */
    protected int size() {
        return taskList.size();
    }

    /**
     * Get task based on index.
     *
     * @param i the
     * @return the task
     */
    protected Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Gets task list.
     *
     * @return the task list
     */
    protected ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    @Override
    public String toString() {
        return taskList.toString();
    }
}
