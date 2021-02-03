package com.weiliang.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.weiliang.DukeException;
import com.weiliang.task.Deadline;
import com.weiliang.task.Event;
import com.weiliang.task.Task;
import com.weiliang.task.TaskList;

/**
 * Main text-based storage facility
 */
public class Storage {

    /**
     * The filename to store to
     */
    private String filename;

    /**
     * Initializes a new storage
     * 
     * @param filename the text file associated
     */
    public Storage(String filename) {
        this.filename = filename;
    }

    /**
     * Stores lists of tasks into textfile
     * 
     * @param tasks the lists of tasks
     */
    public void storeFile(TaskList tasks) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                bufferedWriter.write(task.toFormattedString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Retrieves list of tasks from text document
     * 
     * @return task list associated
     * @throws DukeException if unable to fetch
     */
    public TaskList loadTasks() throws DukeException {
        TaskList tasks = new TaskList();

        // Create if non-existent
        try {
            File file = new File(filename);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line = bufferedReader.readLine();
            while (line != null && !line.isEmpty()) {
                tasks.add(parseTask(line));
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    /**
     * Inner method for parsing information
     * 
     * @param content raw string content
     * @return the parsed task
     */
    private Task parseTask(String content) {
        // Format -> D | 1 | details | timing
        String[] parts = content.split(" \\| ");
        Task task;

        if (parts[0].equals("D")) {
            task = new Deadline(parts[2], parts[3]);
        } else if (parts[0].equals("E")) {
            task = new Event(parts[2], parts[3]);
        } else {
            task = new Task(parts[2]);
        }

        if (parts[1].equals("1")) {
            task.complete();
        }
        return task;
    }

}
