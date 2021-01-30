package com.lirc572.ip;

import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void replaceWith(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void forEach(Consumer<? super Task> action) {
        tasks.forEach(action);
    }

    public void printAll() {
        int index = 0;
        for (Task task : this.tasks) {
            Ui.printLine(String.format("%d.%s", ++index, task.toString()));
        }
    }

    public String toSavedString() {
        // Convert task list to a string
        StringBuilder data = new StringBuilder();
        for (Task task : this.tasks) {
            data.append(task.toSavedString() + "\n");
        }
        return data.toString();
    }

    public int size() {
        return this.tasks.size();
    }

    public void markAsDone(int index) {
        this.tasks.get(index - 1).setIsDone(true);
    }

    public String getTaskString(int index) {
        return this.tasks.get(index - 1).toString();
    }

    /**
     * Returns the name of the specified task
     *
     * @param index The index of the task
     * @return The name of the task
     */
    public String getTaskName(int index) {
        return this.tasks.get(index - 1).getName();
    }

    public String delete(int index) {
        String taskString = this.getTaskString(index);
        this.tasks.remove(index - 1);
        return taskString;
    }
}
