package duke.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> storedTasks) {
        this.taskList = storedTasks;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void clear() {
        this.taskList.clear();
    }

    public Task get(int idx) {
        return this.taskList.get(idx);
    }

    public Task remove(int idx) {
        return this.taskList.remove(idx);
    }

    public int size() {
        return this.taskList.size();
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }
}
