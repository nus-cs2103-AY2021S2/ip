import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList = new ArrayList<>();

    TaskList() {}

    TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void remove(int index) {
        taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }
}
