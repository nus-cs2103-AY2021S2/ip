package zeke;

import java.util.ArrayList;

import zeke.exceptions.CompletedTaskException;

/**
 * TaskList class to store the list of tasks.
 */
public class TaskList {

    private ArrayList<Task> list;

    /**
     * Constructor for TaskList class.
     * Initializes a TaskList object with list of tasks specified.
     *
     * @param list list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to list.
     *
     * @param task task to be added.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Deletes a task from list.
     *
     * @param num number of task to be deleted.
     */
    public Task deleteTask(int num) {
        return list.remove(num - 1);
    }

    /**
     * Checks a task as done.
     *
     * @param task task to be checked as done.
     * @throws CompletedTaskException if task is already completed.
     */
    public void checkAsDone(Task task) throws CompletedTaskException {
        if (task.getDoneStatus()) {
            throw new CompletedTaskException();
        }
        task.done();
        assert task.isDone : "Task not marked as completed yet";
    }

    /**
     * Returns the list of tasks.
     *
     * @return list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

}
