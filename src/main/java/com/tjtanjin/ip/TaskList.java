package com.tjtanjin.ip;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * TaskList manages all tasks and their operations.
 */
public class TaskList {

    //use arraylist to store tasks
    private final Storage storage;
    private final ArrayList<Task> tasks;

    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.loadTasks();
    }

    /**
     * Adds a new task.
     * @param taskType type of task (todo, deadline or event)
     * @param taskName name of task
     * @param taskDates taskDates array of dates (defaults to first element for deadline end date)
     */
    public void addTask(String taskType, String taskName, LocalDate[] taskDates) {
        Task task;
        if (taskType.equalsIgnoreCase("DEADLINE")) {
            task = new Deadline(taskName, "incomplete", taskDates);
        } else if (taskType.equalsIgnoreCase("EVENT")) {
            task = new Event(taskName, "incomplete", taskDates);
        } else {
            task = new ToDo(taskName, "incomplete");
        }
        tasks.add(task);
        storage.saveTask(tasks.size(), "NEW",
                taskName, "incomplete", taskType.toUpperCase(), taskDates);
        int numTasks = tasks.size();
        Ui.showInfo("Task successfully added! You now have " + numTasks + " task(s)!");
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks a task as done.
     * @param index index of task to mark as done
     */
    public void markTaskDone(int index) {
        try {
            Task task = tasks.get(index);
            if (task.getStatus().equals("complete")) {
                Ui.showError("Task is already completed!");
            } else {
                task.markCompleted();
                String taskName = task.getTaskName();
                String taskType = task.getType().toUpperCase();
                LocalDate[] taskDates = task.getDates();
                storage.saveTask(index, "DONE", taskName, "complete", taskType, taskDates);
                Ui.showInfo("Yay your task is done! :D");
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("The specified task index does not exist!");
        }
    }

    /**
     * Deletes a task.
     * @param index index of task to delete
     */
    public void deleteTask(int index) {
        try {
            Task task = tasks.get(index);
            tasks.remove(task);
            String taskName = task.getTaskName();
            String taskType = task.getType().toUpperCase();
            LocalDate[] taskDates = task.getDates();
            storage.saveTask(index, "DELETE", taskName, "complete", taskType, taskDates);
            Ui.showInfo("The following task has been deleted:\n" + task);
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("The specified task index does not exist!");
        }
    }

    /**
     * Shows all tasks to user
     */
    public void showAllTasks() {
        if (tasks.size() == 0) {
            Ui.showInfo("You have no task at the moment!");
        } else {
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i - 1);
                Ui.showInfo(i + "." + task);
            }
        }
    }

    /**
     * Find tasks whose name contains input from user.
     * @param taskName name of task
     */
    public void findTask(String taskName) {
        if (tasks.size() == 0) {
            Ui.showInfo("You have no task at the moment!");
        } else {
            int counter = 0;
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i - 1);
                if (task.getTaskName().contains(taskName)) {
                    counter += 1;
                    Ui.showInfo(counter + "." + task);
                }
            }
            Ui.showInfo("A total of " + counter + " task(s) were found.");
        }
    }
}
