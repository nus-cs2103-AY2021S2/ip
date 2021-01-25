import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = new ArrayList<>();
        this.taskList.addAll(taskList);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void remove(Task task) {
        this.taskList.remove(task);
    }

    public int size() {
        return taskList.size() - 1;
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }
}
