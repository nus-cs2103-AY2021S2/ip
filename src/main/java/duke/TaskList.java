package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Abstracts the list of task.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Creates a taskList with a list of tasks.
     *
     * @param tasks list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>();
        this.tasks.addAll(tasks);
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a new task into the taskList.
     *
     * @param task task that need to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task in the taskList located by index.
     * The index starts from 1.
     *
     * @param index the index of task that need to be deleted in the taskList.
     * @return
     */
    public Task delete(int index) {
        return tasks.remove(index - 1);
    }

    /**
     * Marks a task done located by index.
     * The index starts from 1.
     *
     * @param index the index of task that is done.
     * @return
     */
    public Task mark(int index) {
        Task taskToBeMarked = tasks.get(index - 1);
        taskToBeMarked.markedAsDone();
        return taskToBeMarked;
    }

    /**
     * Finds list of tasks matches the keyword.
     *
     * @param keyword the keyword user inputs.
     * @return list of tasks containing the keyword.
     */
    public TaskList find(String keyword) {
        List<Task> res = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                res.add(task);
            }
        }
        return new TaskList(res);
    }
}
