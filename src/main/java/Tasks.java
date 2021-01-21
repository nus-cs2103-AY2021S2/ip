import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private List<String> listOfTasks;

    public Tasks() {
        listOfTasks = new ArrayList<>();
    }

    public List<String> getListOfTasks() {
        return listOfTasks;
    }

    public void addTask(String task) {
        listOfTasks.add(task);
    }
}
