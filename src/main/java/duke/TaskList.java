package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of all Tasks in a list.
 */
public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Get a specific task from the list.
     * @param index The position of the task in the list.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Add a task to the list.
     * @param task The added task.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Delete a specific task from the list.
     * @param index The position of the task in the list.
     * @return The removed task.
     */
    public Task deleteTask(int index) {
        Task deletedTask = getTask(index);
        taskList.remove(index);
        return deletedTask;
    }

    /**
     * Mark the task at the given index as done.
     * @param index The position of a task in the list.
     */
    public Task markTaskAsDone(int index) {
        Task completedTask = getTask(index).markAsDone();
        taskList.set(index, completedTask);
        return completedTask;
    }

    /**
     * Get the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int numOfTasks() {
        return taskList.size();
    }

    /**
     * list down all tasks in the list.
     * @return the list of all tasks.
     */
    public String listAllTasks() {
        StringBuilder list = new StringBuilder();
        int taskNumber = 1;
        for (Task task : taskList) {
            list.append(taskNumber).append(".").append(task.toString()).append("\n");
            taskNumber++;
        }
        return list.toString();
    }

    /**
     * Checks if the task list has the given task.
     * @param givenTask The given task.
     * @return True if the task list has the given task, false otherwise.
     */
    public boolean hasTask(Task givenTask) {
        String givenTaskString = givenTask.toString();
        for (Task task : taskList) {
            String taskString = task.toString();
            if (taskString.equals(givenTaskString)) {
                return true;
            }
        }
        return false;
    }
}
