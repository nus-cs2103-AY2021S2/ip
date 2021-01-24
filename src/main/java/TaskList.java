import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex - 1);
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}
