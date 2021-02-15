package com.tanboonji.duke.model;

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

    public ArrayList<Task> getList() {
        return taskList;
    }

    public int getSize() {
        return taskList.size();
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
    public Task markAsDone(int index) {
        Task task = taskList.get(index);
        return task.markAsDone();
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
            builder.append("\t")
                    .append(numbering++)
                    .append(". ")
                    .append(task)
                    .append("\n");
        }

        if (numbering == 1) {
            builder.append("\tYou currently have 0 tasks.");
        }
        return builder.toString();
    }
}
