import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    public List<Task> taskList;

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public List<Task> getTaskList() {
        return taskList;
    }


}
