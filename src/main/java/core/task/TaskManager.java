package core.task;

import core.TaskStorage;
import core.TaskStorageFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private ArrayList<Task> allTasks;
    private Path savePath;
    private TaskStorage taskStorage;

    public TaskManager() {
        taskStorage = new TaskStorageFile();
        savePath = Paths.get("data", "taskstore");
        try {
            allTasks = taskStorage.retrieveTaskList(savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTask(Task task) {
        allTasks.add(task);
    }

    public Task retrieveTaskByListID(int id) {
        return allTasks.get(id);
    }

    public Task retrieveTaskByTaskUID(int id) {
        for (var e : allTasks) {
            if (e.getTaskUID() == id) {
                return e;
            }
        }
        return null;
    }

    public void doTaskByListID(int id) {
        var task = retrieveTaskByListID(id);
        if (task.isDone()) {
            throw new TaskAlreadyDoneException();
        } else {
            task.markAsDone();
        }
    }

    public void doTaskByTaskUID(int id) {
        retrieveTaskByTaskUID(id).markAsDone();
    }

    public List<Task> retrieveAllTasks() {
        return allTasks;
    }

    public void deleteTaskByListID(int id) {
        allTasks.remove(id);
    }

    public Path getSavePath() {
        return savePath;
    }

    public void setSavePath(Path savePath) {
        this.savePath = savePath;
    }

    public TaskStorage getTaskStorage() {
        return taskStorage;
    }

    public void setTaskStorage(TaskStorage taskStorage) {
        this.taskStorage = taskStorage;
    }

    public void reloadTaskList() {
        try {
            allTasks = taskStorage.retrieveTaskList(savePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            taskStorage.saveTaskList(allTasks, savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
