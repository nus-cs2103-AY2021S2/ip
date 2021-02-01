package duke;

import duke.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> userList;

    TaskList(ArrayList<Task> userList) {
        this.userList = userList;
    }

    TaskList() {
        this.userList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.userList;
    }

    public int getTaskListSize() {
        return this.userList.size();
    }

    public Task getTask(int index) {
        return this.userList.get(index);
    }

    public void addTask(Task task) {
        this.userList.add(task);
    }

    public Task removeTask(int taskNumber) {
        Task deletedTask = this.userList.remove(taskNumber);
        return deletedTask;
    }

}
