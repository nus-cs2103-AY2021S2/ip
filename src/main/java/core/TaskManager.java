package core;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private ArrayList<Task> allTasks;

    public TaskManager() {
        allTasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        allTasks.add(task);
    }

    public Task retrieveTaskByListID(int id) {
        return allTasks.get(id);
    }

    public Task retrieveTaskByTaskUID(int id) {
        for(var e : allTasks){
            if(e.getTaskUID() == id) {
                return e;
            }
        }
        return null;
    }

    public void doTaskByListID(int id) {
        var task = retrieveTaskByListID(id);
        if(task.isDone()) {
            throw new TaskAlreadyDoneException();
        }
    }

    public void doTaskByTaskUID(int id) {
        retrieveTaskByTaskUID(id).markAsDone();
    }

    public List<Task> retrieveAllTasks() {
        return allTasks;
    }

}
