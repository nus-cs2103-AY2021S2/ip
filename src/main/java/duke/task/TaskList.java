package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task remove(int idx) {
        return taskList.remove(idx);
    }

    public void markDone(int idx) {
        taskList.get(idx).setDone();
    }

    public int size() {
        return taskList.size();
    }

    public String toDataString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(task.toSavedString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
