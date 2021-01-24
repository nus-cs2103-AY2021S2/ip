package duke;

import duke.Tasks.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void markComplete(int n) {
        Task temp = this.taskList.get(n - 1);
        temp.checkTask();
    }

    public void deleteTask(int task) {
        Task temp = this.taskList.get(task - 1);
        this.taskList.remove(task - 1);
    }

    public void storeTask(Task task) {
        this.taskList.add(task);
    }

    public Task getTask(int n) {
        return this.taskList.get(n - 1);
    }
}
