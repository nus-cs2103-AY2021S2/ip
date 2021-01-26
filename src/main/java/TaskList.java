import java.util.List;

/**
 * Wrapper class around list of tasks to handle operations.
 */
public class TaskList {

    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Get underlying list of tasks.
     *
     * @return List of tasks
     */
    public List<Task> getList() {
        return tasks;
    }

    /**
     * Get specific task by index.
     *
     * @param index Index of task
     * @return Task at that index
     */
    public Task get(int index) {
        Task selected = tasks.get(index);
        return selected;
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Mark task as done.
     *
     * @param index Index of task
     * @return Done task.
     */
    public Task markAsDone(int index) {
        Task selected = tasks.get(index);
        selected.markAsDone();
        return selected;
    }

    /**
     * Delete task.
     *
     * @param index Index of task
     * @return Deleted task.
     */
    public Task delete(int index) {
        Task selected = tasks.get(index);
        tasks.remove(index);
        return selected;
    }

    /**
     * Add task.
     *
     * @param task Task to be added
     * @return Done task.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return output;
    }

}