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
 * The main text-based storage facility.
 */
public class Storage {

    /** The name of the file to store to. Does not include file extension. */
    private String fileName;

    /**
     * Initializes a new storage instance.
     * 
     * @param fileName File name of text document.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Stores a list of tasks into text documents.
     * 
     * @param tasks Task list.
     */
    public void storeFile(TaskList tasks) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Task task : tasks) {
                bufferedWriter.write(task.toFormattedString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns list of tasks from text document.
     * 
     * @return Associated {@code TaskList}.
     * @throws DukeException If unable to fetch tasks.
     */
    public TaskList loadTasks() throws DukeException {
        TaskList tasks = new TaskList();

        // Create if non-existent
        try {
            File file = new File(fileName);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read from file
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
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
     * Parses task information from raw data.
     * 
     * @param content Raw string content.
     * @return The parsed task.
     * @throws DukeException If file fails to load.
     */
    private Task parseTask(String content) throws DukeException {
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
            task.markComplete();
        }
        return task;
    }

}
