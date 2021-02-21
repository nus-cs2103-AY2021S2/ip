package duke;

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

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> temp = new ArrayList<Task>();
        for (Task task : this.taskList) {
            if (task.getDescription().contains(keyword)) {
                temp.add(task);
            }
        }
        return temp;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int taskIndex) {
        return this.taskList.remove(taskIndex - 1);
    }

    public Task markDone(Integer taskIndex) {
        Task task = this.taskList.get(taskIndex - 1);
        task.markAsDone();
        return task;
    }

    public Task markUndone(Integer taskIndex) {
        Task task = this.taskList.get(taskIndex - 1);
        task.markAsUndone();
        return task;
    }
}
