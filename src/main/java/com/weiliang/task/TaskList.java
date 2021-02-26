package com.weiliang.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.weiliang.DukeException;

/**
 * A collection to hold the tasks.
 */
public class TaskList implements Iterable<Task> {

    private List<Task> tasks;
    private List<Task> history;

    /**
     * Instantiates a new empty task list.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Instantiates a task list given a set of tasks.
     *
     * @param tasks The tasks to be added.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the size of this list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task, with its given index in the list.
     *
     * @param taskNum The index position of the task.
     * @return The task.
     * @throws DukeException If item cannot be fetched.
     */
    public Task get(int taskNum) throws DukeException {
        if (taskNum >= 0 && taskNum < tasks.size()) {
            return tasks.get(taskNum);
        } else {
            throw new DukeException("Item not found!");
        }
    }

    /**
     * Removes a task given the index position.
     *
     * @param taskNum The index position of the task.
     * @return The removed task.
     * @throws DukeException If unable to remove.
     */
    public Task remove(int taskNum) throws DukeException {
        history = new ArrayList<>(tasks);
        if (taskNum >= 0 && taskNum < tasks.size()) {
            return tasks.remove(taskNum);
        } else {
            throw new DukeException("Item not found!");
        }
    }

    /**
     * Appends the specified task to the end of this list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        history = new ArrayList<>(tasks);
        tasks.add(task);
    }

    /**
     * Returns {@code true} if this list contains the specified task.
     *
     * @param task The task to compare against.
     * @return {@code true} if the list contains given task.
     */
    public boolean contains(Task task) {
        return tasks.contains(task);
    }

    /**
     * Undo a single creation/deletion operation.
     *
     * @throws DukeException If creation/deletion history is empty or pass the limit of one operation.
     */
    public void undo() throws DukeException {
        if (history == null) {
            throw new DukeException("Unable to undo action!");
        }

        tasks = history;
        history = null;
    }

    /**
     * Returns an iterator over the tasks in the list.
     *
     * @return An iterator over the tasks.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

}
