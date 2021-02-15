package duke.tasks;

import java.util.ArrayList;
import java.util.List;

import duke.utils.TaskStringConverter;

/**
 * Models a list of tasks, with operations to add, delete, set task as done and return a string
 * representation of all the tasks in the list.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> converted) {
        this.taskList = new ArrayList<>(converted);
    }

    /**
     * Marks task at specified position to be done.
     *
     * @param pos position of task to be marked.
     */
    public void setTaskDone(int pos) {
        taskList.get(pos).markAsDone();
    }

    public List<Task> getList() {
        return this.taskList;
    }

    /**
     * Adds a task to the existing list of tasks.
     *
     * @param task task to be added to the list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes the task at the specified position from the list.
     *
     * @param pos position of the task to be removed.
     */
    public void deleteTask(int pos) {
        assert pos >= 0 && pos < this.taskList.size();

        this.taskList.remove(pos);
    }

    /**
     * Returns message indicating the contents of the TaskList.
     *
     * @return String message of concatenated List of Tasks, or message indicating an empty list.
     */
    public String getListInString () {
        if (this.taskList.isEmpty()) {
            return getEmptyListInString();
        }
        return getNonEmptyListInString();
    }

    private String getEmptyListInString() {
        assert this.taskList.isEmpty();

        String completedAllTasksMsg = "You have no existing tasks!";
        return completedAllTasksMsg;
    }

    private String getNonEmptyListInString() {
        assert !this.taskList.isEmpty();

        String allTasks = TaskStringConverter.stringTasksForProgram(this.taskList);
        return allTasks;
    }

    public boolean isEmpty() {
        return this.taskList.size() == 0;
    }
}
