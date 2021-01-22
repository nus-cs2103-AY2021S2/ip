import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private List<Task> listOfTasks;

    public Tasks() {
        listOfTasks = new ArrayList<>();
    }

    public List<Task> getListOfTasks() {
        return listOfTasks;
    }

    public void editTask(int taskNum, Task task) {
        listOfTasks.set(taskNum - 1, task);
    }

    public void addTask(String task) {
        listOfTasks.add(new Task(task));
    }
}
