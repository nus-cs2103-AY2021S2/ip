package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles initialisation and maintenance of task list.
 */
public class TaskList {
    protected List<Task> tasks;
    protected Storage storage;

    /**
     * Initialises task list using an arraylist and Storage object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.storage = new Storage();
    }

    /**
     * Gets size of task list.
     *
     * @return Number of tasks in task list.
     */
    public int size() {
        assert tasks != null : "task list is null";
        return tasks.size();
    }

    /**
     * Gets task at index of task list.
     *
     * @param index Index of task to return.
     * @return Task at specified index.
     */
    public Task get(int index) {
        assert tasks != null : "task list is null";
        return tasks.get(index);
    }

    /**
     * Loads task list from storage.
     *
     * @throws DukeException If unable to load from file.
     */
    public void load() throws DukeException {
        assert storage != null : "storage is null";
        try {
            tasks = storage.load();
        } catch (IOException ex) {
            throw new DukeException("  Unable to load tasks.");
        }
    }

    /**
     * Adds a task to list and saves list to storage.
     *
     * @param task Task to add.
     * @throws IOException If unable to save to file.
     */
    public void add(Task task) throws IOException {
        assert tasks != null : "task list is null";
        tasks.add(task);
        updateStorage();
    }

    /**
     * Removes and returns a task from list and saves list to storage.
     *
     * @param index Index of task to remove.
     * @return Task that was removed.
     * @throws IOException If unable to save to file.
     */
    public Task remove(int index) throws IOException {
        assert tasks != null : "task list is null";
        Task task = tasks.remove(index);
        updateStorage();
        return task;
    }

    /**
     * Updates task in list as done.
     *
     * @param index Index of task to update.
     * @throws IOException If unable to save to file.
     */
    public void markAsDone(int index) throws IOException {
        get(index).markAsDone();
        updateStorage();
    }

    /**
     * Updates storage with task list.
     *
     * @throws IOException If unable to save to file.
     */
    public void updateStorage() throws IOException {
        assert storage != null : "storage is null";
        storage.save(tasks);
    }
}
