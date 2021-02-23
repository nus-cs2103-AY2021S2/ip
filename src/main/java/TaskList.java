import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;
    /**
     * Constructor method.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }
    /**
     * Method to get the Task
     * @param index of the Task in the TaskList
     * @return the Task object at the index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }
    /**
     * Method to add the Task
     * @param task to be added
     * @return TaskList with task added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
    /**
     * Method to delete the Task
     * @param index of the Task in the TaskList
     * @return Task object that was deleted
     */
    public Task deleteTask(int index) {
        Task deletedTask = getTask(index);
        tasks.remove(index);
        return deletedTask;
    }
    /**
     * Method to replace the Task
     * @param index of the Task in the TaskList
     * @param task that will be replacing
     * @return replace the Task at the index with task
     */
    public void replaceTask(int index, Task task) {
        tasks.set(index, task);
    }
    /**
     * Method to get the size of TaskList
     * @return the size of TaskList
     */
    public int numOfTasks() {
        return tasks.size();
    }
}
