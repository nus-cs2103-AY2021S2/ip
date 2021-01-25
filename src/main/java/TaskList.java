import java.util.ArrayList;

// CONTAINS THE LIST, HAS ADD & DELETE OPERATIONS
public class TaskList {
    ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void removeTask(int taskIndex) {
        list.remove(taskIndex);
    }

}
