package com.tjtanjin.ip;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * TaskList manages all tasks and their operations.
 */
public class TaskHandler {

    //use arraylist to store tasks
    private final StorageHandler storageHandler;
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     * @param storageHandler storage to store and load tasks from
     */
    public TaskHandler(StorageHandler storageHandler) {
        this.storageHandler = storageHandler;
        this.tasks = storageHandler.loadTasks();
    }

    /**
     * Adds a new task.
     * @param taskType type of task (todo, deadline or event)
     * @param taskName name of task
     * @param taskDates taskDates array of dates (defaults to first element for deadline end date)
     */
    public String addTask(String taskType, String taskName, LocalDate[] taskDates) {
        Task task;
        if (taskType.equalsIgnoreCase("DEADLINE")) {
            task = new Deadline(taskName, "incomplete", taskDates);
        } else if (taskType.equalsIgnoreCase("EVENT")) {
            task = new Event(taskName, "incomplete", taskDates);
        } else {
            task = new ToDo(taskName, "incomplete");
        }
        tasks.add(task);
        if (storageHandler.saveTask(tasks.size(), "NEW",
                taskName, "incomplete", taskType.toUpperCase(), taskDates)) {
            return "Info: Your task has been added! You now have " + tasks.size() + " task(s)!";
        } else {
            return "Error: There was an error saving your task, please check your syntax and try again.";
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks a task as done.
     * @param index index of task to mark as done
     */
    public String markTaskDone(int index) {
        try {
            Task task = tasks.get(index);
            if (task.getStatus().equals("complete")) {
                return "Error: Task is already completed!";
            } else {
                task.markCompleted();
                String taskName = task.getTaskName();
                String taskType = task.getType().toUpperCase();
                LocalDate[] taskDates = task.getDates();
                if (storageHandler.saveTask(index, "DONE",
                        taskName, "complete", taskType, taskDates)) {
                    return "Info: Yay your task is done! :D";
                } else {
                    throw new DukeException("Error: There was an error marking your task as done, "
                            + "please check your syntax and try again.");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return "Error: The specified task index does not exist!";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Deletes a task.
     * @param index index of task to delete
     */
    public String deleteTask(int index) {
        try {
            Task task = tasks.get(index);
            tasks.remove(task);
            String taskName = task.getTaskName();
            String taskType = task.getType().toUpperCase();
            LocalDate[] taskDates = task.getDates();
            if (storageHandler.saveTask(index, "DELETE",
                    taskName, "complete", taskType, taskDates)) {
                return "Info: The following task has been deleted:\n" + task;
            } else {
                throw new DukeException("Error: There was an error deleting your task, "
                        + "please check your syntax and try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            return "Error: The specified task index does not exist!";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Shows all tasks to user
     */
    public String showAllTasks() {
        if (tasks.size() == 0) {
            return "You have no task at the moment!";
        } else {
            StringBuilder str = new StringBuilder("Info: ");
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i - 1);
                str.append(i).append(".").append(task).append("\n");
            }
            str.append("You have ").append(tasks.size()).append(" task(s)!");
            return str.toString();
        }
    }

    /**
     * Find tasks whose name contains input from user.
     * @param taskName name of task
     */
    public String findTask(String taskName) {
        if (tasks.size() == 0) {
            return "Info: You have no task at the moment!";
        } else {
            int counter = 0;
            StringBuilder str = new StringBuilder("Info: ");
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i - 1);
                if (task.getTaskName().contains(taskName)) {
                    counter += 1;
                    str.append(counter).append(".").append(task).append("\n");
                }
            }
            str.append("A total of ").append(counter).append(" task(s) were found.");
            return str.toString();
        }
    }
}
