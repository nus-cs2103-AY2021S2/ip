package com.nus.duke.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.nus.duke.common.DukeStorageException;
import com.nus.duke.data.Task;
import com.nus.duke.data.TaskList;

/**
 * TaskListStorage class handles the loading and saving of data from the disk.
 */
public class TaskListStorage {

    public static final String DEFAULT_STORAGE_FILEPATH = Paths
            .get(System.getProperty("user.home"), "duke-tasklist.data").toString();
    public static final String SAVE_FAILURE_MESSAGE = "Unable to save to file. Your data may not be saved.";
    public static final String LOAD_FAILURE_MESSAGE = "Unable to read from file.";

    /**
     * Saves the taskList to disk.
     *
     * @param taskList taskList to be saved
     * @throws DukeStorageException on any file access exception
     */
    public void save(TaskList taskList) throws DukeStorageException {
        ArrayList<Task> arrayList = taskList.getList();
        try {
            FileOutputStream fileOut = new FileOutputStream(DEFAULT_STORAGE_FILEPATH);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(arrayList);
            out.close();
        } catch (IOException e) {
            throw new DukeStorageException(SAVE_FAILURE_MESSAGE);
        }
    }

    /**
     * Loads the existing taskList from disk.
     *
     * @return existing taskList or new taskList
     * @throws DukeStorageException on any file access exception
     */
    public TaskList load() throws DukeStorageException {
        FileInputStream fileIn;
        try {
            fileIn = new FileInputStream(DEFAULT_STORAGE_FILEPATH);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Task> tasks = (ArrayList<Task>) in.readObject();
            in.close();
            fileIn.close();
            return new TaskList(tasks);
        } catch (IOException | ClassNotFoundException e) {
            throw new DukeStorageException(LOAD_FAILURE_MESSAGE);
        }
    }
}
