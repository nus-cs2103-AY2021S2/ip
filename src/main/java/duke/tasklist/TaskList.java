package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    public int size() {
        return this.taskList.size();
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public Task get(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    public TaskList getTaskList() {
        return new TaskList(this.taskList);
    }

    public int getTaskCount(Class<? extends Task> cls) {
        int count = 0;
        for (Task task : this.taskList) {
            if (task.getClass() == cls) {
                count += 1;
            }
        }
        return count;
    }

    public TaskList findTasks(String keyword) {
        ArrayList<Task> temp = new ArrayList<Task>();
        for (Task task : this.taskList) {
            if (task.getDescription().contains(keyword)) {
                temp.add(task);
            }
        }
        return new TaskList(temp);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int taskIndex) {
        return this.taskList.remove(taskIndex - 1);
    }

    public Task markDone(int taskIndex) {
        Task task = this.taskList.get(taskIndex - 1);
        task.markAsDone();
        return task;
    }

    public Task markUndone(int taskIndex) {
        Task task = this.taskList.get(taskIndex - 1);
        task.markAsUndone();
        return task;
    }
}
