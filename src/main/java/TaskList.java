import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<String> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(final String task) {
        taskList.add(task);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            out.append(i);
            out.append(". ");
            out.append(taskList.get(i));

            if (i != taskList.size() - 1) {
                out.append('\n');
            }
        }

        return out.toString();
    }
}
