package com.tjtanjin.ip;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 * TaskList manages all tasks and their operations.
 */
public class TaskList {

    //use arraylist to store tasks
    public static ArrayList<Task> tasks = Storage.loadTasks();

    /**
     * Adds a new task.
     * @param taskType type of task (todo, deadline or event)
     * @param taskName name of task
     * @param taskDate due date of task
     */
    public static void addTask(String taskType, String taskName, LocalDate taskDate) {
        Task task;
        if (taskType.equalsIgnoreCase("DEADLINE")) {
            task = new Deadline(taskName, "incomplete", taskDate);
        } else if (taskType.equalsIgnoreCase("EVENT")) {
            task = new Event(taskName, "incomplete", taskDate);
        } else {
            task = new ToDo(taskName, "incomplete");
        }
        tasks.add(task);
        int numTasks = tasks.size();
        Ui.showInfo("Task successfully added! You now have " + numTasks + " task(s)!");
    }

    /**
     * Marks a task as done.
     * @param index index of task to mark as done
     */
    public static void markTaskDone(int index) {
        try {
            Task task = tasks.get(index);
            if (task.getStatus().equals("complete")) {
                Ui.showError("Task is already completed!");
            } else {
                task.markCompleted();
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
    public static void deleteTask(int index) {
        try {
            Task task = tasks.get(index);
            tasks.remove(task);
            Ui.showInfo("The following task has been deleted:\n" + task);
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("The specified task index does not exist!");
        }
    }

    /**
     * Shows all tasks to user
     */
    public static void showAllTasks() {
        if (tasks.size() == 0) {
            Ui.showInfo("You have no task at the moment!");
        } else {
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i - 1);
                Ui.showInfo(i + "." + task);
            }
        }
    }

    public static void findTask(String taskName) {
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
