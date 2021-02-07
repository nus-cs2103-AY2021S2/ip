package main.java;

import java.util.ArrayList;
import java.util.List;

import main.java.entity.Task;

/**
 * Contain a Task List to manage tasks
 * Could add, markDone, delete, read tasks
 * It uses Storage class to help manage task storage on disk
 */
public class TaskManager {
    private List<Task> list;
    private Storage storage;

    /**
     * Creates a TaskManager to manage Tasks
     * Tasks in a filepath to store tasks info on disk
     * @param filepath path to the file for disk storage
     */
    public TaskManager(String filepath) {
        storage = new Storage(filepath);
        list = storage.readAll();
    }

    /**
     * Get task list size
     * @return size of the current task list
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Add Task to task list
     * @param task task to be added
     * @return the added task
     */
    public Task addTask(Task task) {
        list.add(task);
        storage.updateFile(list);
        return task;
    }

    /**
     * Change the particular task's isDone status to true
     * @param index index of done task in list
     * @return the done task
     */
    public Task done(int index) {
        Task task = list.get(index);
        task.markAsDone();
        storage.updateFile(list);
        return task;
    }

    /**
     * Delete the particular task from list
     * @param index index of task-to-delete in list
     * @return the deleted task
     */
    public Task deleteTask(int index) {
        Task task = list.remove(index);
        storage.updateFile(list);
        return task;
    }

    /**
     * Delete all tasks appearing in parameter list
     * @param taskList list of tasks to be deleted
     * @return the deleted taskList
     */
    public List<Task> deleteTask(List<Task> taskList) {
        for (Task task : taskList) {
            list.remove(task);
        }
        storage.updateFile(list);
        return taskList;
    }

    /**
     * Get the task list
     * @return task list
     */
    public List<Task> getList() {
        return this.list;
    }

    /**
     * Check if the task list is Empty
     * @return true if list is empty
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Check if a particular index falls in the range of list
     * @param index input index to be checked
     * @return true if index is valid
     */
    public boolean indexWithinRange(int index) {
        return index < size() && index >= 0;
    }

    /**
     * Search through the existing task list for a specific keyword
     * Add all occurrences of the tasks to return list
     * @param keyword keyword string to search
     * @return list of tasks containing the keyword
     */
    public List<Task> findByKeyword(String keyword) {
        List<Task> result = new ArrayList<>();
        for (Task task : list) {
            if (task.getName().contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }
}
