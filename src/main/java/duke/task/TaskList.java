package duke.task;

import duke.exception.DukeException;
import java.util.LinkedList;

/**
 * Represents a list of task.
 */
public class TaskList {
    LinkedList<Task> list;

    /**
     * Constructor for <code>TaskList</code>.
     */
    public TaskList() {
        this.list = new LinkedList<>();
    }

    /**
     * Overloaded constructor for <code>TaskList</code>.
     *
     * @param list a LinkedList that contains <code>Task</code>
     */
    public TaskList(LinkedList<Task> list) {
        this.list = new LinkedList<Task>(list);
    }

    /**
     * Add a <code>Task</code> into <code>TaskList</code>.
     *
     * @param task a task to be added
     * @return the task that is added
     */
    public Task addTask(Task task) {
        list.add(task);
        return task;
    }

    /**
     * Remove a <code>Task</code> from <code>TaskList</code>.
     * This method will remove a <code>Task</code> by the position in <code>TaskList</code>
     * and return the removed task.
     *
     * @param pos the position of the task to be removed (1-indexed)
     * @return the task that is removed
     * @throws DukeException
     */
    public Task removeTask(int pos) throws DukeException {
        if (pos > list.size() || pos < 0) {
            throw new DukeException(
                    String.format("Tried to delete nothing ????. (Size: %d | Task No: %d)", list.size(), pos));
        }
        Task task = this.list.remove(pos - 1);
        return task;
    }

    /**
     * Mark a <code>Task</code> as done.
     * This method will mark a <code>Task</code> done by the position in <code>TaskList</code> and
     * return the task that is marked.
     *
     * @param pos the position of the task to be marked (1-indexed)
     * @return the task that is marked
     * @throws DukeException
     */
    public Task markDone(int pos) throws DukeException {
        if (pos > this.list.size() || pos < 0) {
            throw new DukeException(
                    String.format("Tried to mark nothing ????. (Size: %d | Task No: %d)", list.size(), pos));
        }
        this.list.get(pos - 1).setDone();
        return this.list.get(pos - 1);
    }

    /**
     * Returns a LinkedList of all tasks in <code>TaskList</code>.
     *
     * @return a LinkedList of tasks
     */
    public LinkedList<Task> getList() {
        return this.list;
    }

}
