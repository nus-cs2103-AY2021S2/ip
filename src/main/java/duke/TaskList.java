package duke;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Collection;

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs new task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs new task list with existing list.
     *
     * @param tasks Collection of tasks.
     */
    public TaskList(Collection<Task> tasks) {
        this.taskList = new ArrayList<>(tasks);
    }

    /**
     * Adds task into task list.
     *
     * @param t task to be added into list.
     */
    public void add(Task t) {
        taskList.add(t);
    }

    /**
     * Removes a task from the list.
     *
     * @param num index of task to be removed.
     * @return removed task.
     */
    public Task remove(int num) throws DukeNumOutOfRangeException {
        try {
            return taskList.remove(num);
        } catch (Exception e) {
            throw new DukeNumOutOfRangeException();
        }
    }

    /**
     * Returns task from the list.
     *
     * @param num index of task.
     * @return task of certain index.
     */
    public Task get(int num) throws DukeNumOutOfRangeException {
        try {
            return taskList.get(num);
        } catch (Exception e) {
            throw new DukeNumOutOfRangeException();
        }

    }

    /**
     * Replaces task of certain index.
     *
     * @param taskNum index of task replaced.
     * @param t task that is replacing.
     */
    public void set(int taskNum, Task t) throws DukeNumOutOfRangeException {
        try {
            taskList.set(taskNum, t);
        } catch (Exception e) {
            throw new DukeNumOutOfRangeException();
        }
    }

    /**
     * Returns task list in ArrayList
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getList() {
        return new ArrayList<Task>(taskList);
    }

    public int size() {
        return taskList.size();
    }

    /**
     * Returns string format of all task in list.
     *
     * @return concatenated string of tasks in list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (Task t : taskList) {
            sb.append(t.saveTask());
        }
        return sb.toString();
    }
}
