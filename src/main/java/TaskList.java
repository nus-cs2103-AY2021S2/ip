import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable  {

    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void markTaskAsDone(int index) {
        this.getTask(index).markAsDone();
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }
}
