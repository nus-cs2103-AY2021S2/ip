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

    public TaskManager() {this(true);}

    public void addTask(Task task) {
        allTasks.add(task);
    }

    public Task retrieveTaskByListId(int id) {
        return allTasks.get(id);
    }

    public Task retrieveTaskByTaskUid(int id) {
        for (var e : allTasks) {
            if (e.getTaskUid() == id) {
                return e;
            }
        }
        return null;
    }

    public void doTaskByListId(int id) {
        var task = retrieveTaskByListId(id);
        if (task.isDone()) {
            throw new TaskAlreadyDoneException();
        } else {
            task.setDone();
        }
    }

    public void doTaskByTaskUid(int id) {
        retrieveTaskByTaskUid(id).setDone();
    }

    public List<Task> retrieveAllTasks() {
        return allTasks;
    }

    public void deleteTaskByListId(int id) {
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
