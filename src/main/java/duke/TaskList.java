package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private Ui ui;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.ui = new Ui();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    public ListIterator<Task> getIterator() {
        return this.tasks.listIterator();
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Add task to the list.
     * @param task duke.Tasks.Task entered by the user.
     */

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Remove task from the list.
     * @param taskIndex The index of the task to remove.
     */

    public Task deleteTask(int taskIndex) {
        Task toRemove = tasks.get(taskIndex);
        toRemove.setNotDone();
        tasks.remove(taskIndex);
        return toRemove;
    }
}
