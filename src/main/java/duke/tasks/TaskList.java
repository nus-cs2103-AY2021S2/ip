package duke.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public int size() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void completeTask(int index) {
        taskList.get(index).completeTask();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof TaskList) {
            TaskList otherTaskList = (TaskList) obj;
            if (this.taskList != null && otherTaskList.taskList != null) {
                return this.taskList.equals(otherTaskList.taskList);
            } else {
                return this.taskList == null && otherTaskList.taskList == null;
            }
        } else {
            return false;
        }
    }
}
