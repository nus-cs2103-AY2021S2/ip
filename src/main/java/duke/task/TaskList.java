package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList keeps all information of task for the session
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        taskList = new ArrayList<>();
        taskList.add(null);
    }

    /**
     * Overloaded constructor for TaskList for loading from storage file
     *
     * @param taskList
     */
    public TaskList(List<Task> taskList) {
        this.taskList = new ArrayList<>();
        this.taskList.add(null);
        this.taskList.addAll(taskList);
    }

    /**
     * Size of TaskList, excluding index 0
     *
     * @return Size of TaskList
     */
    public int size() {
        return taskList.size() - 1;
    }

    /**
     * Adds task to TaskList
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes specified task from TaskList
     *
     * @param task Task to be removed
     */
    public void remove(Task task) {
        taskList.remove(task);
    }

    /**
     * Retrieves Task from TaskList
     *
     * @param i Index of task in TaskList
     * @return Task to be retrieved
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Returns a list of task
     *
     * @return A list of task
     */
    public List<Task> asList() {
        return taskList;
    }

}
