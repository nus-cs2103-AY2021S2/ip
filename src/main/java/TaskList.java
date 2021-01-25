import java.util.List;

/**
 * Represents a list of tasks for a user.
 * A TaskList also handles operations for the tasks in the list.
 */
public class TaskList {

    protected List<Task> tasks;

    public TaskList(List<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     * @param taskNumber Task number of the task that is to be deleted.
     */
    public void delete(int taskNumber) {
        tasks.remove(taskNumber);
    }

    /**
     * Prints out all the tasks in the task list.
     */
    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Returns a Task, based on the task number specified by users.
     * @param taskNumber Task number of selected task.
     * @return Task.
     */
    public Task find(int taskNumber) {
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
