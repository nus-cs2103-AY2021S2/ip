import java.util.ArrayList;
import java.util.Collection;
import java.lang.StringBuilder;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(Collection<Task> tasks) {
        this.taskList = new ArrayList<>(tasks);
    }

    public void add(Task t) {
        taskList.add(t);
    }

    public Task remove(int num) {
        return taskList.remove(num - 1);
    }

    public Task get(int num) {
        return taskList.get(num - 1);
    }

    public void set(int num, ) {

    }

    public void doneTask
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (Task t : taskList) {
            sb.append(t.saveTask());
        }
        return sb.toString();
    }
}