package com.tanboonji.jhin.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The TaskList class contains the list of task.
 */
public class TaskList implements Serializable {

    private final ArrayList<Task> taskList;

    /**
     * Class constructor specifying an array list of task for task list to be initialised to.
     *
     * @param taskList Array list of task.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns a TaskList of tasks which contains the keyword in its description.
     *
     * @param keyword Keyword to search for in task description.
     * @return TaskList of tasks containing keyword in its description.
     */
    public TaskList find(String keyword) {
        ArrayList<Task> matchingList = new ArrayList<>();

        for (Task task: taskList) {
            if (task.containsText(keyword)) {
                matchingList.add(task);
            }
        }

        return new TaskList(matchingList);
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task New task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks a task as done in task list by index in array list.
     *
     * @param index Index of task in array list.
     * @return Task that is marked as done.
     */
    public Task markTaskAsDone(int index) {
        Task task = taskList.get(index);
        return task.markAsDone();
    }

    /**
     * Checks if a task is done in task list by index in array list.
     *
     * @param index Index of task in array list.
     * @return True if task is done, else false.
     */
    public boolean isTaskDone(int index) {
        return taskList.get(index).isDone();
    }

    /**
     * Deletes a task in task list by index in array list.
     *
     * @param index Index of task in array list.
     * @return Task that is deleted.
     */
    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int numbering = 1;
        for (Task task: taskList) {
            builder.append(numbering++)
                    .append(". ")
                    .append(task)
                    .append("\n");
        }

        return builder.toString();
    }
}
