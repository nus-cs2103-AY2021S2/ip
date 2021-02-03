package com.weiliang.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.weiliang.DukeException;

public class TaskList implements Iterable<Task> {

    private List<Task> tasks;

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
        if (taskNum >= 0 && taskNum < tasks.size()) {
            return tasks.remove(taskNum);
        } else {
            throw new DukeException("Item not found!");
        }
    }

    public void add(Task task) throws DukeException {
        if (!tasks.add(task)) {
            throw new DukeException("Failed to add task!");
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

}
