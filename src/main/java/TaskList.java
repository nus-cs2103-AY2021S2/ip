import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        Task deletedTask = getTask(index);
        tasks.remove(index);
        return deletedTask;
    }

    public void replaceTask(int index, Task task) {
        tasks.set(index, task);
    }

    public int numOfTasks() {
        return tasks.size();
    }
}
