package duke;

import java.util.ArrayList;

import duke.tasks.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Deletes a task at the index given from the taskList.
     * Index begins from 1.
     *
     * @param index index of task to be deleted
     * @return Task deleted
     */
    public Task delete(int index) {
        assert(index >= 0);
        return tasks.remove(index - 1);
    }

    /**
     * Marks the task given by index to be done.
     * Index begins from 1.
     *
     * @param index the index of task to be marked as done
     * @return Task marked done
     */
    public Task mark(int index) {
        assert(index >= 0);
        Task task = tasks.get(index - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Returns a list of tasks which matches the keyword.
     *
     * @param keyword the keyword user input
     * @return list of tasks containing the keyword
     */
    public TaskList find(String keyword) {
        ArrayList<Task> arr = new ArrayList<>();
        for (Task task : tasks) {
            boolean gotKeyword = task.getDescription().contains(keyword);
            if (gotKeyword) {
                arr.add(task);
            }
        }
        return new TaskList(arr);
    }

    /**
     * Adds a new Task into the TaskList.
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        assert(task != null);
        tasks.add(task);
    }
}
