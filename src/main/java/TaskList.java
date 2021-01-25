import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(List<Task> tasks) {
        this.taskList = new ArrayList();
        this.taskList.addAll(tasks);
    }

    public TaskList() {
        this.taskList = new ArrayList();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task delete(int index) {
        return taskList.remove(index - 1);
    }

    public Task mark(int index) {
        Task taskToMark = taskList.get(index - 1);
        taskToMark.markAsDone();
        return taskToMark;
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}
