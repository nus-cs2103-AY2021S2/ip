package yoda.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import yoda.task.TaskList;

/**
 * Storage class to handle storing and retrieval of the tasklist from the hard disk.
 */
public class Storage {
    /** Filepath of the file containing the tasklist */
    private String filePath;

    /**
     * Creates a storage object.
     * @param filePath Location of file on the hard disk.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() {
        File tasks = new File(filePath);
        TaskList taskList = new TaskList(new ArrayList<>());
        boolean isNewFile = false;
        try {
            if (tasks.createNewFile()) {
                System.out.println("Created new file " + tasks.getName());
                System.out.println("Location: " + tasks.getAbsolutePath());
                isNewFile = true;
            } else {
                System.out.println("Used existing file");
                System.out.println("Location: " + tasks.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred when trying to create the file during load");
        }
        if (!isNewFile) {
            return deserialize();
        } else {
            return taskList;
        }
    }

    public void serialize(TaskList taskList) {
        File tasks = new File(filePath);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(tasks);
            ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);
            objOutputStream.writeObject(taskList);
            objOutputStream.flush();
            objOutputStream.close();
            System.out.println("Wrote to file");
            System.out.println("Location: " + tasks.getAbsolutePath());
        } catch (FileNotFoundException e) {
            System.out.println("File not found when serializing!");
        } catch (IOException e) {
            System.out.println("An error occurred when serializing");
        }
    }

    /**
     * Deserializes the content in the file.
     * @return TaskList obtained from the deserialization.
     */
    public TaskList deserialize() {
        TaskList taskList = new TaskList(new ArrayList<>());
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream);
            taskList = (TaskList) objInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found when deserializing!");
        } catch (IOException e) {
            System.out.println("An error occurred when deserializing!");
        } catch (ClassNotFoundException e) {
            System.out.println("Reinstall Yoda or contact the developer!");
        }
        return taskList;
    }
}
