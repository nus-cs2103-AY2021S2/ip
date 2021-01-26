import java.util.List;
public class TaskList {
    private List<Task> listOfTasks;

    public TaskList(List<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public int size() {
        return this.listOfTasks.size();
    }

    public void add(Task task) {
        listOfTasks.add(task);
    }

    public Task get(int i) {
        return this.listOfTasks.get(i);
    }

    public Task set(int i, Task task) {
        return this.listOfTasks.set(i, task);
    }

    public Task remove(int i) {
        return this.listOfTasks.remove(i);
    }
}
