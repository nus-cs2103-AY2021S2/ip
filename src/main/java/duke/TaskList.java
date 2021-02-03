package duke;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * To get current total count of tasks in a list.
     *
     * @return total count of tasks.
     */
    public int getTasksCount() {
        return this.taskList.size();
    }

    /**
     * To get current task list
     * @return current task list
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * To set a task's completion status.
     *
     * @param index Position of the task in the list.
     */
    public void setTaskIsDone(int index, boolean isDone) {
        this.taskList.get(index).setIsDone(isDone);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

}
