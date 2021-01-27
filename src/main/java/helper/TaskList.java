package helper;

import task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = new ArrayList<>();
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public Task get(int index) {
        return taskList.get(index);
    }


    public void add(Task task) {
        taskList.add(task);
    }

    public void remove(int index) {
        taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
