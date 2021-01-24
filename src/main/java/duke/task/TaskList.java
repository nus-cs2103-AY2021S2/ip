package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns a list of task which match the given keyword.
     *
     * @return List of tasks which match the given keyword.
     */
    public TaskList findTask(String keyword) {
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < getTaskCount(); ++i) {
            Task task = getTask(i);
            Boolean isFound = task.getDescription().toLowerCase().contains(keyword.toLowerCase());
            if (isFound) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }
}
