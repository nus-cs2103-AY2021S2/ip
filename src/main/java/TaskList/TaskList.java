package TaskList;

import Tasks.DukeTask;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<DukeTask> list;

    public TaskList() {
        this.list = new ArrayList<DukeTask>();
    }

    public TaskList(List<DukeTask> list) {
        this.list = list;
    }

    /**
     * Adds a DukeTask into the TaskList.
     *
     * @param task DukeTask we want to add.
     */
    public void add(DukeTask task) {
        this.list.add(task);
    }

    /**
     * Marks a DukeTask in the TaskList as done.
     *
     * @param index Index of DukeTask we want to mark as done.
     */
    public void done(int index) {
        DukeTask task = this.list.get(index - 1).markDone();
        this.list.set(index - 1, task);
    }

    /**
     * Deletes a DukeTask in the TaskList.
     *
     * @param index Index of DukeTask we want to delete.
     */
    public void delete(int index) {
        this.list.remove(index - 1);
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return Number of DukeTasks in the list.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Returns the List of DukeTasks.
     *
     * @return List of DukeTasks.
     */
    public List<DukeTask> getList() {
        return this.list;
    }

    /**
     * Returns the DukeTasks in the TaskList at the specific index.
     *
     * @param index Index of the DukeTask we want to retrieve
     * @return The DukeTask we want to retrieve.
     */
    public DukeTask getTask(int index) {
        return this.list.get(index - 1);
    }

}
