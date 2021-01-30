package yoda.storage;

import yoda.task.Task;
import yoda.task.TaskList;

import java.io.*;
import java.util.ArrayList;

/**
 * Storage class to handle storing and retrieval of the tasklist from the hard disk.
 */
public class Storage {
    /** Filepath of the file containing the tasklist */
    String filePath;

    /**
     * Creates a storage object.
     * @param filePath Location of file on the hard disk.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasklist from the file specified in filepath.
     * @return TaskList stored in the file.
     */
    public TaskList load() {
        File tasks = new File(filePath);
        System.out.println("1");
        TaskList taskList = new TaskList(new ArrayList<Task>());
        boolean newFile = false;
        try {
            if (tasks.createNewFile()) {
                System.out.println("Created new file " + tasks.getName());
                System.out.println("Location: " + tasks.getAbsolutePath());
                newFile = true;
            } else {
                System.out.println("Used existing file");
                System.out.println("Location: " + tasks.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred when trying to create the file");
        }
        if (!newFile) {
            return deserialize();
        } else {
            return taskList;
        }
    }

    /**
     * Writes the tasklist to the file specified in the filepath.
     * @param taskList TaskList to be stored in the file.
     */
    public void write(TaskList taskList) {
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
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("An error occurred");
        }
    }

    /**
     * Deserializes the content in the file.
     * @return TaskList obtained from the deserialization.
     */
    public TaskList deserialize() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream);
            taskList = (TaskList) objInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("An error occurred");
        } catch (ClassNotFoundException e) {
            System.out.println("Reinstall Duke");
        }
        return taskList;
    }
}
