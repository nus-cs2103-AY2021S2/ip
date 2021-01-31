package duke;

import java.util.ArrayList;
import java.util.Collection;
import java.lang.StringBuilder;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(Collection<Task> tasks) {
        this.taskList = new ArrayList<>(tasks);
    }

    /**
     * Add task into task list.
     *
     * @param t task to be added into list.
     */
    public void add(Task t) {
        taskList.add(t);
    }

    /**
     * Remove a task from the list.
     *
     * @param num index of task to be removed.
     * @return removed task.
     */
    public Task remove(int num) {
        return taskList.remove(num);
    }

    /**
     * Return task from the list.
     *
     * @param num index of task.
     * @return task of certain index.
     */
    public Task get(int num) {
        return taskList.get(num);
    }

    /**
     * Replace task of certain index.
     *
     * @param taskNum index of task replaced.
     * @param t task that is replacing.
     */
    public void set(int taskNum, Task t) {
        taskList.set(taskNum, t);
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