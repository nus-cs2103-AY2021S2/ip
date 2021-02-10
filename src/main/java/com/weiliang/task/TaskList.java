package com.weiliang.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.weiliang.DukeException;

public class TaskList implements Iterable<Task> {

    private List<Task> tasks;
    private List<Task> history;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int taskNum) throws DukeException {
        if (taskNum >= 0 && taskNum < tasks.size()) {
            return tasks.get(taskNum);
        } else {
            throw new DukeException("Item not found!");
        }
    }

    public Task remove(int taskNum) throws DukeException {
        history = new ArrayList<>(tasks);
        if (taskNum >= 0 && taskNum < tasks.size()) {
            return tasks.remove(taskNum);
        } else {
            throw new DukeException("Item not found!");
        }
    }

    public void add(Task task) {
        history = new ArrayList<>(tasks);
        tasks.add(task);
    }

    public boolean contains(Task task) {
        return tasks.contains(task);
    }

    public void undo() throws DukeException {
        if (history == null) {
            throw new DukeException("Unable to undo action!");
        }

        tasks = history;
        history = null;
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

}
