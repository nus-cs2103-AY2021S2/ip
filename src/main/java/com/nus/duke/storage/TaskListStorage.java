package com.nus.duke.storage;

import com.nus.duke.common.DukeStorageException;
import com.nus.duke.data.Task;
import com.nus.duke.data.TaskList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TaskListStorage {

    public static final String DEFAULT_STORAGE_FILEPATH = "duke-tasklist.data";
    public static final String SAVE_FAILURE_MESSAGE = "Unable to save to file. Your data may not be saved.";
    public static final String LOAD_FAILURE_MESSAGE = "Unable to read from file.";

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
