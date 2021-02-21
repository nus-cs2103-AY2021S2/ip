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

    public int getSize() {
        return this.taskList.size();
    }

    public int getCompletedSize() {
        int count = 0;
        for (Task task : this.taskList) {
            if (task.getStatusIcon().equals("*")) {
                count += 1;
            }
        }
        return count;
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

    public void deleteTask(Integer taskIndex) {
        this.taskList.remove(taskIndex);
    }

    public void markDone(Integer taskIndex) {
        this.taskList.get(taskIndex).markAsDone();
    }
}
