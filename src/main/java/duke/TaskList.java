package duke;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Encapsulates the information and operations of a task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Deletes a task specified by task id from the task list.
     * @param taskId The task id of the task which will be deleted.
     * @return The task which is deleted.
     * @throws DukeException Exception if there is error when deleting the task.
     */
    public Task deleteTask(int taskId) throws DukeException {
        try {
            Task task = tasks.get(taskId);
            tasks.remove(taskId);
            return task;
        } catch (Exception e) {
            throw new DukeException("Delete task error!");
        }
    }

    /**
     * Adds a task to the task list.
     * @param task The task which will be added to the task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks the task specified by the task id as done.
     * @param taskId The task id of the task which will be marked as done.
     * @return The task which is marked as done.
     * @throws DukeException Exception if there is error when marking the task as done.
     */
    public Task doneTask(int taskId) throws DukeException {
        try {
            Task task = tasks.get(taskId);
            task.done();
            return task;
        } catch (Exception e) {
            throw new DukeException("Done task error!");
        }
    }

    public String toStringWithIndex() {
        String str = "";
        for (int i = 0; i < getNumOfTasks(); i++) {
            str = str + (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return str;
    }

    /**
     * Find the task that the keyword totally or partially matches it.
     *
     * @param keyword The search keyword input from users.
     * @return
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Returns a string describing the task list.
     * @return A string which is used to be stored in the file for storage.
     */
    public String toString() {
        String str = "";
        for (Task task: tasks) {
            str = str + task.toString() + "\n";
        }
        return str;
    }

    /**
     * Returns the number of current tasks in the task list.
     * @return The number of current tasks in the task list.
     */
    public int getNumOfTasks() {
        return this.tasks.size();
    }
}
