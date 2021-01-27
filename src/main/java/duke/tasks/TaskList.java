package duke.tasks;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(Task task) {
        taskList.remove(task);
    }

    public void removeTask(Integer i) {
        taskList.remove(i);
    }

    public Task getTask(Integer i) {
        return taskList.get(i);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
