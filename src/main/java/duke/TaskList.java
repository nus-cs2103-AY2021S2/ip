package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of <code>Task</code> objects.
 * @see Task
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * This method adds a Task object to the list.
     * @param task A <code>Task</code> object.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * This method deletes the selected <code>Task</code> object.
     * @param index The index of the selected <code>Task</code> to delete.
     * @return The deleted <code>Task</code> object.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * This method is used to mark the selected <code>Task</code>
     * as done.
     * @param index The index of <code>Task</code> to mark as done.
     * @return The selected <code>Task</code> object.
     */
    public Task markTaskAsDone(int index) {
        return tasks.get(index).markAsDone();
    }

    /**
     * This method is used to get the selected <code>Task</code> object.
     * @param index The index of <code>Task</code> to get.
     * @return The selected <code>Task</code> object.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * This method is used to find a list of tasks that match the specified keywords.
     * @param keywords The specified keywords to find tasks.
     * @return <code>TaskList</code> of tasks that match the specified keywords.
     * @see TaskList
     */
    public TaskList findTasks(String keywords) {
        TaskList foundTasks = new TaskList();
        for(Task task : tasks) {
            if(task.getDescription().contains(keywords)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    /**
     * This method is used to check whether the list is empty.
     * @return True if list is empty. Else, false.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * This method is used to get size of list.
     * @return Size of the list.
     */
    public int getSize() {
        return tasks.size();
    }
}
