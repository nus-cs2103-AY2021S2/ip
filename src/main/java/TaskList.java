import java.util.ArrayList;
import java.util.Collection;

public class TaskList {
    private ArrayList<Task> taskList;


    public TaskList(Collection<Task> taskCollection) {
        this.taskList = new ArrayList<>(taskCollection);
    }
}
