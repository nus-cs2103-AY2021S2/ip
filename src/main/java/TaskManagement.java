package surrealchat.task;

import java.util.List;

public class TaskManagement {
    public List<Task> taskList;

    public TaskManagement(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task markAsDone(int taskNumber) {
        Task doneTask = this.taskList.get(taskNumber - 1);
        doneTask.markAsDone();
        this.taskList.set(taskNumber - 1, doneTask);
        return doneTask;
    }

    public Task markAsUndone(int taskNumber) {
        Task undoneTask = this.taskList.get(taskNumber - 1);
        undoneTask.markAsUndone();
        this.taskList.set(taskNumber - 1, undoneTask);
        return undoneTask;
    }

    public Task deleteTask(int taskNumber) {
        return this.taskList.remove(taskNumber - 1);
    }
}
