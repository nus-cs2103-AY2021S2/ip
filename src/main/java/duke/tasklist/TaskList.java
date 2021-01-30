package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores all the tasks
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task into the task list
     * @param task the current instance of task list used by Duke
     */
    public void addTask(Task task) {
        taskList.add(task);
        Task.incrementNumOfTask();
    }

    /**
     * Removes the task from task list
     * @param taskNumber the current instance of task list used by Duke
     */
    public void removeTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
        Task.decrementNumOfTask();
    }

    /**
     * Removes the task from task list
     * @param task the current instance of task list used by Duke
     */
    public void removeTask(Task task) {
        taskList.remove(task);
        Task.decrementNumOfTask();
    }

    public List<Task> filterTasks(String searchString) {
        List<Task> filteredTaskList = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.getTaskName().contains(searchString)) {
                filteredTaskList.add(task);
            }
        }
        return filteredTaskList;
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    @Override
    public String toString() {
        String taskListString = "";
        for (int i = 0; i < taskList.size(); i++) {
            String taskString = (i + 1) + ". " + taskList.get(i);
            taskListString = taskListString + taskString
                    + (i == taskList.size() - 1 ? "" : "\n");
        }
        return taskListString;
    }
}
