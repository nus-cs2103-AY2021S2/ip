import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(final Task task) {
        taskList.add(task);
    }

    public String doneTask(final int idx) {
        taskList.get(idx - 1).markDone();
        return taskList.get(idx - 1).toString();
    }

    public String deleteTask(final int idx) {
        return taskList.remove(idx - 1).toString();
    }

    public int size() {
        return taskList.size();
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            out.append(i + 1);
            out.append(". ");
            out.append(taskList.get(i));

            if (i != taskList.size() - 1) {
                out.append('\n');
            }
        }

        return out.toString();
    }
}
