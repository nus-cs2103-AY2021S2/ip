package com.tjtanjin.steve.tasks;

import java.time.LocalDate;
import java.util.ArrayList;

import com.tjtanjin.steve.storage.StorageHandler;

/**
 * TaskHandler manages all task operations. It is managed through the CommandHandler and works directly
 * with StorageHandler to store/load tasks.
 */
public class TaskHandler {

    //use arraylist to store tasks
    private final StorageHandler STORAGE_HANDLER;
    private final ArrayList<Task> TASKS;

    /**
     * Constructor for TaskList.
     *
     * @param storageHandler storage to store and load tasks from
     */
    public TaskHandler(StorageHandler storageHandler) {
        this.STORAGE_HANDLER = storageHandler;
        this.TASKS = storageHandler.loadTasks();
    }

    /**
     * Adds a new task.
     *
     * @param taskType type of task (todo, deadline or event)
     * @param taskName name of task
     * @param taskDates taskDates array of dates (defaults to first element for deadline end date)
     * @return string response after operation is done
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
        TASKS.add(task);
        if (STORAGE_HANDLER.saveTask(TASKS.size(), "NEW",
                taskName, "incomplete", taskType.toUpperCase(), taskDates)) {
            return "Info: Your task has been added! You now have " + TASKS.size() + " task(s)!";
        } else {
            return "Error: There was an error saving your task, please check your syntax and try again.";
        }
    }

    /**
     * Gets array list of tasks.
     *
     * @return array list of tasks
     */
    public ArrayList<Task> getTasks() {
        return TASKS;
    }

    /**
     * Marks a task as done.
     *
     * @param index index of task to mark as done
     * @return string response after operation is done
     */
    public String markTaskDone(int index) {
        try {
            Task task = TASKS.get(index);
            if (task.getStatus().equals("complete")) {
                return "Error: Task is already completed!";
            } else {
                task.markCompleted();
                String taskName = task.getTaskName();
                String taskType = task.getType().toUpperCase();
                LocalDate[] taskDates = task.getDates();
                if (STORAGE_HANDLER.saveTask(index, "DONE",
                        taskName, "complete", taskType, taskDates)) {
                    return "Info: Yay your task is done! :D";
                } else {
                    return "Error: There was an error marking your task as done, "
                            + "please check your syntax and try again.";
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return "Error: The specified task index does not exist!";
        }
    }

    /**
     * Deletes a task.
     *
     * @param index index of task to delete
     * @return string response after operation is done
     */
    public String deleteTask(int index) {
        try {
            Task task = TASKS.get(index);
            TASKS.remove(task);
            String taskName = task.getTaskName();
            String taskType = task.getType().toUpperCase();
            LocalDate[] taskDates = task.getDates();
            if (STORAGE_HANDLER.saveTask(index, "DELETE",
                    taskName, "complete", taskType, taskDates)) {
                return "Info: The following task has been deleted:\n" + task;
            } else {
                return "Error: There was an error deleting your task, "
                        + "please check your syntax and try again.";
            }
        } catch (IndexOutOfBoundsException e) {
            return "Error: The specified task index does not exist!";
        }
    }

    /**
     * Shows all tasks to user
     *
     * @return string response after operation is done
     */
    public String showAllTasks() {
        if (TASKS.size() == 0) {
            return "Info: You have no task at the moment!";
        } else {
            StringBuilder str = new StringBuilder("Info: ");
            for (int i = 1; i <= TASKS.size(); i++) {
                Task task = TASKS.get(i - 1);
                str.append(i).append(".").append(task).append("\n");
            }
            str.append("You have ").append(TASKS.size()).append(" task(s)!");
            return str.toString();
        }
    }

    /**
     * Finds all tasks whose name contains input from user.
     *
     * @param taskName name of task
     * @return string response after operation is done
     */
    public String findTask(String taskName) {
        if (TASKS.size() == 0) {
            return "Info: You have no task at the moment!";
        } else {
            int counter = 0;
            StringBuilder str = new StringBuilder("Info: ");
            for (int i = 1; i <= TASKS.size(); i++) {
                Task task = TASKS.get(i - 1);
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
