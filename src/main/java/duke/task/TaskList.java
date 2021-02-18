package duke.task;

import java.util.Comparator;
import java.util.LinkedList;

import duke.exception.DukeException;

/**
 * Represents a list of task.
 */
public class TaskList {
    private LinkedList<Task> list;
    private Comparator<Task> compareDate;
    /**
     * Constructor for <code>TaskList</code>.
     */
    public TaskList() {
        this.list = new LinkedList<>();
        initComparator();
    }

    /**
     * Overloaded constructor for <code>TaskList</code>.
     *
     * @param list a LinkedList that contains <code>Task</code>
     */
    public TaskList(LinkedList<Task> list) {
        this.list = new LinkedList<Task>(list);
        initComparator();
    }

    /**
     * Adds a <code>Task</code> into <code>TaskList</code>.
     *
     * @param task a task to be added
     * @return the task that is added
     */
    public Task addTask(Task task) {
        list.add(task);
        return task;
    }

    /**
     * Removes a <code>Task</code> from <code>TaskList</code>.
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
     * Marks a <code>Task</code> as done.
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

    /**
     * Search for all the <code>Task</code> and return a <code>TaskList</code>
     * Search for all the <code>Task</code> in <code>TaskList</code> that contains a series of character.
     * Return a new <code>TaskList</code>
     *
     * @param str a series of character to use for finding
     * @return a new TaskList containing all the found <code>Task</code>
     */
    public TaskList findTask(String str) {
        LinkedList<Task> foundList = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().contains(str)) {
                foundList.add(list.get(i));
            }
        }
        return new TaskList(foundList);
    }

    /**
     * Initialises the comparator for sort by date
     * This method will be called when creating a new <code>TaskList</code>
     */
    public void initComparator() {
        this.compareDate = (x, y) -> {
            if (x.getDate() == null) {
                return -1;
            } else if (y.getDate() == null) {
                return 1;
            } else {
                return x.getDate().compareTo(y.getDate());
            }
        };
    }

    /**
     * Sorts the list by date
     */
    public void sortListByDate() {
        list.sort(compareDate);
    }
}
