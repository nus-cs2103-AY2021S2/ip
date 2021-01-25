package mike;

import mike.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * A List of Tasks
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Creates a taskList from a list of Task Objects
     * @param tasks
     */
    public TaskList(List<Task> tasks) {
        this.taskList = new ArrayList();
        this.taskList.addAll(tasks);
    }

    /**
     * Creates an empty taskList
     */
    public TaskList() {
        this.taskList = new ArrayList();
    }

    /**
     * Add a task to the taskList
     *
     * @param task the task to be added to the taskList
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes the Task at the index of the list
     *
     * @param index the index of the Task to be deleted
     * @return the Task Object that was removed from the list
     */
    public Task delete(int index) {
        return taskList.remove(index - 1);
    }

    /**
     * Mark the Task at the index of the taskList as done
     *
     * @param index the index of the Task to be marked as done
     * @return the Task Object that was marked as done
     */
    public Task mark(int index) {
        Task taskToMark = taskList.get(index - 1);
        taskToMark.markAsDone();
        return taskToMark;
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}
