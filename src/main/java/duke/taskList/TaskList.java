package duke.taskList;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * A taskList class which stores Task objects in an ArrayList object.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds a Task object into the taskList
     *
     * @param task
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a Task object from the taskList based on
     * index position of the task.
     *
     * @param i, the index position of the task Object
     */
    public Task removeTask(int i) {
        return taskList.remove(i);
    }

    /**
     * Returns the number of tasks in the taskList
     *
     * @return int, size of the taskList
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns the Task object at the specified index
     *
     * @param i, index of the task
     * @return Task, at the given index
     */
    public Task getTask(int i) {
        return taskList.get(i);
    }
}