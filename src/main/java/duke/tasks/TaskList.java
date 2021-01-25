package duke.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public int size() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void completeTask(int index) {
        taskList.get(index).completeTask();
    }
}
