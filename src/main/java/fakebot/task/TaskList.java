package fakebot.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Get Task at Index i.
     *
     * @param i Index of Task.
     * @return Return Task.
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Add task to task list.
     *
     * @param task Task to add to TaskList.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Remove Task index i.
     *
     * @param i Index of Task to be removed.
     */
    public void removeTask(int i) {
        tasks.remove(i);
    }

    /**
     * Get size of task list.
     *
     * @return Return Size of Task List.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Find all task that contain search string
     *
     * @param search String to search
     * @return Return list of task that contain search string
     */
    public List<Task> find(String search) {
        List<Task> foundTask = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskName().contains(search)) {
                foundTask.add(tasks.get(i));
            }
        }
        return foundTask;
    }
}
