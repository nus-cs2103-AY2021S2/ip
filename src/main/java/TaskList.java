import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public List<Task> getTaskList() {
        return this.tasks;
    }

    public int getTaskListLength() {
        return this.tasks.size();
    }

    public Task getTaskAtIndex(int index) {
        return this.tasks.get(index);
    }
}