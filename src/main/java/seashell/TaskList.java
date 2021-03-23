package seashell;

import java.util.ArrayList;

import seashell.task.Task;


public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public Task remove(int index) {
        return this.taskList.remove(index);
    }

    public void set(int index, Task updated) {
        this.taskList.set(index, updated);
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return this.taskList.size();
    }

    public void clear() {
        this.taskList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof TaskList
                && this.taskList.equals(((TaskList) other).taskList));
    }
}
