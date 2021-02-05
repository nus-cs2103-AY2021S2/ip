package todobeast;

import java.util.List;
import java.util.ArrayList;

import todobeast.tasks.Task;

/**
 * Wrapper class that stores all tasks for the application into a List.
 */
public class TaskList {
    protected List<Task> taskList;

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

    public boolean isTaskIndexInRange(int taskNumber) {
        return taskNumber >= 0 && taskNumber < taskList.size();
    }

    public int getNumOfTasks() {
        return taskList.size();
    }

    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
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
