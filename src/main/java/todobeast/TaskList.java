package todobeast;

import java.util.ArrayList;
import java.util.List;

import todobeast.tasks.Task;

/**
 * Wrapper class that stores all tasks for the application into a List.
 */
public class TaskList {
    protected final List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    public void setTaskAsDone(Task task) {
        task.setDone();
    }

    public void setTaskNotes(Task task, String taskNotes) {
        task.setTaskNotes(taskNotes);
    }

    public void clearTaskNotes(Task task) {
        task.clearTaskNotes();
    }

    public boolean isTaskIndexInRange(int taskNumber) {
        return taskNumber >= 0 && taskNumber < taskList.size();
    }

    public int getNumOfTasks() {
        return taskList.size();
    }

    /**
     * Deletes the task with the specified task number from the task list.
     * @param taskNumber the index of the task number to be deleted.
     * @return the task that has been deleted from the task list.
     */
    public Task deleteTask(int taskNumber) {
        Task deletedTask = getTask(taskNumber);
        taskList.remove(taskNumber - 1);
        return deletedTask;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     *Formats all tasks in the present TaskList into a format suitable for storing into a data text file.
     * @return a String containing all the tasks that have been formatted by this method
     */
    public String formatTaskListForStorage() {
        StringBuilder outputString = new StringBuilder();
        for (Task task : taskList) {
            outputString.append(task.formatForStorage(Storage.STORAGE_DELIMITER));
            outputString.append("\n");
        }
        return outputString.toString();
    }
}
