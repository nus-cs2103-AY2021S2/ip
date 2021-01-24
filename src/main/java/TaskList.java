import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
        taskList.add(null);
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }


}
