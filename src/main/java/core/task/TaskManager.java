package core.task;

import core.TaskStorage;
import core.TaskStorageFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages all the operations on the list of tasks, both active and done. Central class to manage tasks.
 */
public class TaskManager {
    private ArrayList<Task> allTasks;
    private Path savePath;
    private TaskStorage taskStorage;

    /**
     * Creates a new TaskManager, either from storage, or afresh.
     * @param loadFromStore - whether to load from store.
     */
    public TaskManager(boolean loadFromStore) {
        taskStorage = new TaskStorageFile();
        if(!loadFromStore) {
            allTasks = new ArrayList<>();
            return;
        }

        savePath = Paths.get("data", "taskstore");
        try {
            allTasks = taskStorage.retrieveTaskList(savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new TaskManager from storage. If storage is empty, then creates a new list.
     */
    public TaskManager() {this(true);}

    /**
     * Adds a task.
     * @param task - task to be added.
     */
    public void addTask(Task task) {
        allTasks.add(task);
    }

    /**
     * Returns the task corresponding to the list index given.
     * @param id - list index
     * @return - corresponding Task
     */
    public Task retrieveTaskByListId(int id) {
        return allTasks.get(id);
    }

    /**
     * Returns the task corresponding to the task UID given.
     * @param id - task UID
     * @return - corresponding Task
     */
    public Task retrieveTaskByTaskUid(int id) {
        for (var e : allTasks) {
            if (e.getTaskUid() == id) {
                return e;
            }
        }
        return null;
    }

    /**
     * Performs the task corresponding to the list index.
     * @param id - list index
     */
    public void doTaskByListId(int id) {
        var task = retrieveTaskByListId(id);
        if (task.isDone()) {
            throw new TaskAlreadyDoneException();
        } else {
            task.setDone();
        }
    }

    /**
     * Performs the task corresponding to the task UID.
     * @param id - Task UID
     */
    public void doTaskByTaskUid(int id) {
        retrieveTaskByTaskUid(id).setDone();
    }

    /**
     * Returns a list of all tasks.
     * @return - list of tasks
     */
    public List<Task> retrieveAllTasks() {
        return allTasks;
    }

    /**
     * Deletes task corresponding to List index given.
     * @param id - list index.
     */
    public void deleteTaskByListId(int id) {
        allTasks.remove(id);
    }

    /**
     * Returns the {@code Path} where the storage is to be saved.
     * @return - {@code Path}
     */
    public Path getSavePath() {
        return savePath;
    }

    /**
     * Sets the {@code Path} where everything is to be saved.
     * @param savePath - {@code Path}
     */
    public void setSavePath(Path savePath) {
        this.savePath = savePath;
    }

    /**
     * Gets the {@code TaskStorage} implementation.
     * @return - {@code TaskStorage}
     */
    public TaskStorage getTaskStorage() {
        return taskStorage;
    }

    /**
     * Sets the {@code TaskStorage} implementation
     * @return - {@code TaskStorage} type.
     */
    public void setTaskStorage(TaskStorage taskStorage) {
        this.taskStorage = taskStorage;
    }

    /**
     * Reloads the task list from the given storage.
     */
    public void reloadTaskList() {
        try {
            allTasks = taskStorage.retrieveTaskList(savePath);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    /**
     * Saves the tasklist to the assigned storage.
     */
    public void save() {
        try {
            taskStorage.saveTaskList(allTasks, savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
