package com.tanboonji.jhin.storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.tanboonji.jhin.exception.JhinException;
import com.tanboonji.jhin.model.AliasMap;
import com.tanboonji.jhin.model.TaskList;

/**
 * The Storage class manages the loading and saving of data from the local disk.
 */
public class Storage {

    private static final String LOAD_ERROR_MESSAGE =
            "Sorry, something went wrong while I was loading saved data from file.";
    private static final String SAVE_ERROR_MESSAGE = "Sorry, something went wrong while I was saving data to file.";
    private static final String TASK_DIR = "task.data";
    private static final String ALIAS_DIR = "alias.data";

    /**
     * Default class constructor.
     */
    public Storage() {
    }

    /**
     * Loads task list saved on local disk.
     * If task list does not exist, initialise an empty task list.
     *
     * @return Task list saved on disk, if it does not exist, an empty task list is returned instead.
     * @throws JhinException If any error occurs while loading task list from disk.
     */
    public TaskList loadTask() throws JhinException {
        try {
            FileInputStream fileIn = new FileInputStream(TASK_DIR);
            ObjectInputStream input = new ObjectInputStream(fileIn);
            TaskList taskList = (TaskList) input.readObject();
            input.close();
            fileIn.close();
            return taskList;
        } catch (FileNotFoundException e) {
            return new TaskList(new ArrayList<>());
        } catch (IOException | ClassNotFoundException e) {
            throw new JhinException(LOAD_ERROR_MESSAGE);
        }
    }

    /**
     * Saves task list to disk.
     *
     * @param taskList Task list to be stored to disk.
     * @throws JhinException If any error occurs while saving task list to disk.
     */
    public void saveTask(TaskList taskList) throws JhinException {
        try {
            FileOutputStream fileOut = new FileOutputStream(TASK_DIR);
            ObjectOutputStream output = new ObjectOutputStream(fileOut);
            output.writeObject(taskList);
            output.close();
            fileOut.close();
        } catch (IOException e) {
            throw new JhinException(SAVE_ERROR_MESSAGE);
        }
    }

    /**
     * Loads alias saved on local disk.
     * If alias does not exist, initialise an empty alias hash map.
     *
     * @return Alias saved on disk, if it does not exist, an empty alias hash map is returned instead.
     * @throws JhinException If any error occurs while loading alias from disk.
     */
    public AliasMap loadAlias() throws JhinException {
        try {
            FileInputStream fileIn = new FileInputStream(ALIAS_DIR);
            ObjectInputStream input = new ObjectInputStream(fileIn);
            AliasMap alias = (AliasMap) input.readObject();
            input.close();
            fileIn.close();
            return alias;
        } catch (FileNotFoundException e) {
            return new AliasMap(new HashMap<>());
        } catch (IOException | ClassNotFoundException e) {
            throw new JhinException(LOAD_ERROR_MESSAGE);
        }
    }

    /**
     * Saves alias saved to disk.
     *
     * @param alias Alias to be stored to disk.
     * @throws JhinException If any error occurs while saving alias to disk.
     */
    public void saveAlias(AliasMap alias) throws JhinException {
        try {
            FileOutputStream fileOut = new FileOutputStream(ALIAS_DIR);
            ObjectOutputStream output = new ObjectOutputStream(fileOut);
            output.writeObject(alias);
            output.close();
            fileOut.close();
        } catch (IOException e) {
            throw new JhinException(SAVE_ERROR_MESSAGE);
        }
    }
}
