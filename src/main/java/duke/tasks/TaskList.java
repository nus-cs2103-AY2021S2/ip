package duke.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of {@code Task}.
 */
public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Checks if the list of tasks is empty.
     *
     * @return True if there are no tasks in the list, else false.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Returns the number of tasks in the list of tasks.
     *
     * @return Number of tasks in task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param index Index of the task to be removed in the list.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Returns a task from the list of tasks according to the index specified.
     *
     * @param index Index of the task to be retrieved.
     * @return {@code Task}.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Marks a task in the list of tasks as completed.
     *
     * @param index Index of the task to be marked as completed.
     */
    public void completeTask(int index) {
        taskList.get(index).completeTask();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TaskList)) {
            return false;
        }

        TaskList otherTaskList = (TaskList) obj;
        if (taskList != null && otherTaskList.taskList != null) {
            return taskList.equals(otherTaskList.taskList);
        }
        return taskList == null && otherTaskList.taskList == null;
    }
}
