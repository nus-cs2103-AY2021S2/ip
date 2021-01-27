package surrealchat.task;

import java.util.List;

/**
 * Handles storing of tasks.
 */
public class TaskManagement {
    protected List<Task> taskList;

    /**
     * Creates instance of TaskManagement object.
     * @param taskList List of tasks.
     */
    public TaskManagement(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns List of tasks for further processing.
     * @return List of tasks.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds task to internal task list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Changes the description of a task designated by a number.
     * @param taskNumber Position number of task (starting from 1) to be edited.
     * @param newDescription New description of the task.
     * @return Task that has been edited.
     */
    public Task editDescription(int taskNumber, String newDescription) {
        Task editedTask = this.taskList.get(taskNumber - 1).editDescription(newDescription);
        this.taskList.set(taskNumber - 1, editedTask);
        return editedTask;
    }

    /**
     * Marks a task designated by number as done.
     * @param taskNumber Position number of task (starting from 1) to be marked as done.
     * @return Task that has been marked as done.
     */
    public Task markAsDone(int taskNumber) {
        Task doneTask = this.taskList.get(taskNumber - 1).markAsDone();
        this.taskList.set(taskNumber - 1, doneTask);
        return doneTask;
    }

    /**
     * Marks a task designated by number as undone.
     * @param taskNumber Position number of task (starting from 1) to be marked as undone.
     * @return Task that has been marked as undone.
     */
    public Task markAsUndone(int taskNumber) {
        Task undoneTask = this.taskList.get(taskNumber - 1).markAsUndone();
        this.taskList.set(taskNumber - 1, undoneTask);
        return undoneTask;
    }

    /**
     * Deletes a task from the list.
     * @param taskNumber Position number of task (starting from 1) to be deleted.
     * @return Deleted task.
     */
    public Task deleteTask(int taskNumber) {
        return this.taskList.remove(taskNumber - 1);
    }
}
