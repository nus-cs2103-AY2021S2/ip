import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskNum) {
        tasks.remove(taskNum);
    }

    public void markDone(int taskNum) {
        tasks.get(taskNum).markDone();
    }
}
