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

    public List getTaskList() {
        return taskList;
    }

    public void printTasks() {
        int count = 1;
        for (Task task : taskList) {
            System.out.println(count++ + ". " + task);
        }
    }
}
