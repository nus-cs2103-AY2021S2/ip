import java.util.List;

/**
 * Wrapper class around list of tasks to handle operations.
 */
public class TaskList {

    private List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Get underlying list of tasks.
     * @return List of tasks
     */
    public List<Task> getList() {
        return list;
    }

    /**
     * Get specific task by index.
     * @param index Index of task
     * @return Task at that index
     */
    public Task get(int index) {
        Task selected = list.get(index);
        return selected;
    }

    public int size() {
        return list.size();
    }

    /**
     * Mark task as done.
     * @param index Index of task
     * @return Done task.
     */
    public Task markAsDone(int index) {
        Task selected = list.get(index);
        selected.markAsDone();
        return selected;
    }

    /**
     * Delete task.
     * @param index Index of task
     * @return Deleted task.
     */
    public Task delete(int index) {
        Task selected = list.get(index);
        list.remove(index);
        return selected;
    }

    /**
     * Add task.
     * @param task Task to be added
     * @return Done task.
     */
    public void add(Task task) {
        this.list.add(task);
    }
    
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += (i + 1) + ". " + list.get(i).toString() + "\n";
        }
        return output;
    }

}