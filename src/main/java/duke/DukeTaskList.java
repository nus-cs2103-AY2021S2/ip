package duke;

import task.Task;

import java.util.ArrayList;

public class DukeTaskList {
    protected ArrayList<Task> taskList;

    DukeTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    protected void add(Task t) {
        taskList.add(t);
    }

    protected void remove(int i) {
        taskList.remove(i);
    }

    protected int size() {
        return taskList.size();
    }

    protected Task get(int i) {
        return taskList.get(i);
    }

    protected ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    @Override
    public String toString() {
        return taskList.toString();
    }
}
