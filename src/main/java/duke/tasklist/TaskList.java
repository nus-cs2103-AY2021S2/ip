package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
        Task.incrementNumOfTask();
    }

    public void removeTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
        Task.decrementNumOfTask();
    }

    public void removeTask(Task task) {
        taskList.remove(task);
        Task.decrementNumOfTask();
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    @Override
    public String toString() {
        String taskListString = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            String taskString = (i + 1) + ". " + taskList.get(i);
            taskListString = taskListString + taskString
                    + (i == taskList.size() - 1 ? "" : "\n");
        }
        return taskListString;
    }
}
