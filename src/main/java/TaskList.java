import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    public void addTask(Task task) {
        super.add(task);
    }

    public Task deleteTask(int index) {
        return super.remove(index);
    }
}