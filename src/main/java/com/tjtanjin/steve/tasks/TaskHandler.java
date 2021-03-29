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
    private ArrayList<Task> tasks;
    private final ArrayList<ArrayList<Task>> TASKS_TRACKER = new ArrayList<>();

    /**
     * Constructor for TaskList.
     *
     * @param storageHandler storage to store and load tasks from
     */
    public TaskHandler(StorageHandler storageHandler) {
        this.STORAGE_HANDLER = storageHandler;
        this.tasks = storageHandler.loadTasks();
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

        TASKS_TRACKER.add((ArrayList<Task>) tasks.clone());
        tasks.add(task);
        if (STORAGE_HANDLER.saveTask(tasks.size(), "NEW",
                taskName, "incomplete", taskType.toUpperCase(), taskDates)) {
            return "Info: Your task has been added! You now have " + tasks.size() + " task(s)!";
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
        return tasks;
    }

    /**
     * Marks a task as done.
     *
     * @param index index of task to mark as done
     * @return string response after operation is done
     */
    public String markTaskDone(int index) {
        try {
            Task task = tasks.get(index);
            if (task.getStatus().equals("complete")) {
                return "Error: Task is already completed!";
            }

            TASKS_TRACKER.add((ArrayList<Task>) tasks.clone());
            Task completedTask = task.markCompleted();
            tasks.set(index, completedTask);
            String taskName = task.getTaskName();
            String taskType = task.getType().toUpperCase();
            LocalDate[] taskDates = task.getDates();
            if (STORAGE_HANDLER.saveTask(index, "DONE",
                    taskName, "complete", taskType, taskDates)) {
                return "Info: Yay your task is done! :D";
            }

            return "Error: There was an error marking your task as done, "
                    + "please check your syntax and try again.";
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
            Task task = tasks.get(index);
            TASKS_TRACKER.add((ArrayList<Task>) tasks.clone());
            System.out.println(tasks.size());
            tasks.remove(task);
            System.out.println(tasks.size());
            String taskName = task.getTaskName();
            String taskType = task.getType().toUpperCase();
            LocalDate[] taskDates = task.getDates();
            if (STORAGE_HANDLER.saveTask(index, "DELETE",
                    taskName, "complete", taskType, taskDates)) {
                return "Info: The following task has been deleted:\n" + task;
            }

            return "Error: There was an error deleting your task, "
                    + "please check your syntax and try again.";
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
        if (tasks.size() == 0) {
            return "Info: You have no task at the moment!";
        } else {
            StringBuilder strOfTasks = new StringBuilder("Info: ");
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i - 1);
                strOfTasks.append(i).append(".").append(task).append("\n");
            }
            strOfTasks.append("You have ").append(tasks.size()).append(" task(s)!");
            return strOfTasks.toString();
        }
    }

    /**
     * Finds all tasks whose name contains input from user.
     *
     * @param searchTerms search terms for finding tasks
     * @return string response after operation is done
     */
    public String findTask(String ... searchTerms) {
        if (tasks.size() == 0) {
            return "Info: You have no task at the moment!";
        } else {
            int counter = 0;
            StringBuilder strOfTasks = new StringBuilder("Info: ");
            for (String searchTerm : searchTerms) {
                for (int j = 1; j <= tasks.size(); j++) {
                    Task task = tasks.get(j - 1);
                    if (task.getTaskName().contains(searchTerm)) {
                        counter += 1;
                        strOfTasks.append(counter).append(".").append(task).append("\n");
                    }
                }
            }
            strOfTasks.append("A total of ").append(counter).append(" task(s) were found.");
            return strOfTasks.toString();
        }
    }

    /**
     * Undos the previous task modification.
     *
     * @return string response after operation is done
     */
    public String undoTaskAction() {
        if (TASKS_TRACKER.size() > 0 && tasks.size() > 0) {
            int index = TASKS_TRACKER.size() - 1;
            this.tasks = TASKS_TRACKER.remove(index);
            if (STORAGE_HANDLER.revertTask(tasks)) {
                return "Info: Undo successful for previous task modification!";
            } else {
                return "Error: There was an error undo-ing your task, please check your syntax and try again.";
            }
        } else {
            return "Error: There is no action to undo!";
        }
    }
}
