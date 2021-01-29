package duke.task;

import duke.exception.DukeException;

import java.util.List;
import java.util.ArrayList;

/**
 * TaskList contains the list that is storing the current tasks.
 * Mainly deal with the addition, deletion and manipulation of tasks in the list.
 */
public class TaskList {
    private final List<Task> list;

    /**
     * Returns a TaskList object with an empty list if no parameter was passed in.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Returns a TaskList object with a list that is being passed as the parameter.
     *
     * @param list List storing the tasks.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    public List<Task> getList() {
        return list;
    }

    /**
     * Returns the number of tasks in the list, basically the size of the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Add the given task into the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task){
        list.add(task);
    }

    /**
     * Delete the given task from the list.
     *
     * @param num The index of the task.
     * @return The deleted task.
     * @throws DukeException If num is smaller or equal 0 or num is bigger than the
     * size of the list.
     */
    public Task delete(String num) throws DukeException {
        int index = Integer.valueOf(num) - 1;

        if (index < 0 || index >= list.size()) {
            throw new DukeException("Please enter an appropriate index.");
        }

        return list.remove(index);
    }

    /**
     * Marks the given task as done inside the list.
     *
     * @param num The index of the task.
     * @return The task that is mark as done.
     * @throws DukeException If num is smaller or equal 0 or num is bigger than the
     * size of the list.
     */
    public Task markTaskAsDone(String num) throws DukeException{
        int index = Integer.valueOf(num) - 1;

        if (index < 0 || index >= list.size()) {
            throw new DukeException("Please enter an appropriate index.");
        }

        Task t = list.get(index);
        t.markAsDone();

        return t;
    }

    /**
     * Returns a string representing of all the tasks inside the list. The string
     * is then written to the hard disk. String representation of all done tasks
     * are followed by string representation of all pending tasks.
     *
     * @return A string representation of all tasks inside the list.
     */
    public String listOutTaskInString() {
        String res = "";

        res += "Done tasks: " + System.lineSeparator();

        for (Task t: list) {
            if (t.getIsDone()) {
                res += t.toFileString() + System.lineSeparator();
            }
        }

        res += "Pending tasks: " + System.lineSeparator();

        for (Task t: list) {
            if (!t.getIsDone()) {
                res += t.toFileString() + System.lineSeparator();
            }
        }

        return res;
    }

    /**
     * Returns a list of tasks that contains the keyword.
     *
     * @param keyword The keyword to filter out the irrelevant tasks.
     * @return A list of tasks containing the keyword.
     */
    public List<Task> findTasksWithKeyword(String keyword) {
        List<Task> resTasks = new ArrayList<>();

        for (Task t: list) {
            if (t.isKeywordInside(keyword)) {
                resTasks.add(t);
            }
        }

        return resTasks;
    }
}
