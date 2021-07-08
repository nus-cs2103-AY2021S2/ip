import java.util.List;

/**
 * Represents a list of tasks for a user.
 * A TaskList also handles operations for the tasks in the list.
 */
public class TaskList {

    protected List<Task> tasks;

    /**
     * Creates a TaskList.
     *
     * @param tasks Tasks to be added into the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        assert task != null;
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumber Task number of the task that is to be deleted.
     */
    public void delete(int taskNumber) {
        assert tasks.size() > 0;
        assert taskNumber >= 0 && taskNumber < tasks.size();
        tasks.remove(taskNumber);
    }

    /**
     * Lists out all the tasks in the task list.
     * @return A string consisting of all the tasks.
     */
    public String list() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output = output + "\n" + (i + 1) + "." + tasks.get(i).toString();
        }
        return output;
    }

    /**
     * Returns a Task, based on the task number specified by users.
     *
     * @param taskNumber Task number of selected task.
     * @return Task.
     */
    public Task find(int taskNumber) {
        assert taskNumber >= 0 && taskNumber < tasks.size();
        return tasks.get(taskNumber);
    }

    /**
     * Returns the number of tasks in the task list.
     * @return Number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

}
