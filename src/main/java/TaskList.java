import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;
    private int numTasks = 0;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTaskToList(Task task) {
        taskList.add(task);
        numTasks++;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 1; i <= numTasks; i++) {
            buffer.append(i);
            buffer.append(". ");
            buffer.append(taskList.get(i-1).toString());
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
