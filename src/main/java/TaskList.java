import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {

    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task markAsDone(int index) {
        Task task = this.taskList.get(index);
        return task.markAsDone();
    }

    public Task deleteTask(int index) {
        return this.taskList.remove(index);
    }
}
