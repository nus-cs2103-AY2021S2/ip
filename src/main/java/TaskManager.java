package main.java;
import main.java.entity.*;
import main.java.exceptions.IllegalInputFormatException;
import main.java.exceptions.TaskDoesNotExistException;
import main.java.exceptions.UnrecognizableInputException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> list;
    private Storage storage;

    public TaskManager(String filepath) {
        storage = new Storage(filepath);
        list = storage.readAll();
    }

    public int size() {
        return this.list.size();
    }

    public void addTask(Task task) {
        list.add(task);
        storage.updateFile(list);
    }

    public Task done(int index) {
        Task task = list.get(index);
        task.markAsDone();
        storage.updateFile(list);
        return task;
    }

    public Task deleteTask(int index) {
        Task task = list.remove(index);
        storage.updateFile(list);
        return task;
    }

    public List<Task> getList() {
        return this.list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean indexWithinRange(int index) {
        return index < size() && index >= 0;
    }

    /**
     * Search through the existing task list for a specific keyword
     * Add all occurrences of the tasks to return list
     * @param keyword keyword string to search
     * @return list of tasks containing the keyword
     */
    public List<Task> findByKeyword(String keyword) {
        List<Task> result =  new ArrayList<>();
        for (Task task: list) {
            if (task.getName().contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }
}
