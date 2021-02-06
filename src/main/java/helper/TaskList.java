package helper;

import task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * List of tasks
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = new ArrayList<>();
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Get task from index
     * @param index
     * @return
     */
    public Task get(int index) {
        assert taskList != null;
        return taskList.get(index);
    }

    /**
     * Add task
     * @param task
     */
    public void add(Task task) {
        assert taskList != null;
        taskList.add(task);
    }

    /**
     * Remove task
     * @param index
     */
    public void remove(int index) {
        assert taskList != null;
        taskList.remove(index);
    }

    /**
     *
     * @return Size of tasklist
     */
    public int size() {
        assert taskList != null;
        return taskList.size();
    }

    /**
     * Get task list as a List of tasks
     * @return List of tasks
     */
    public List<Task> getTaskList() {
        assert taskList != null;
        return taskList;
    }
}
