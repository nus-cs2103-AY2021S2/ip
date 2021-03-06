import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    //storage format:
    //<type>, <done>, <description>, <deadline>
    private ArrayList<String> itemList;
    private String path;

    /**
     * Constructor for Storage, a wrapper class for handling file storage.
     * @param path address of file to be used for storage
     */
    public Storage (String path) {
        File logFile = new File(path);
        assert logFile != null : "logFile not created properly. ";
        try {
            if (logFile.isFile()) {
                Scanner logs = new Scanner(logFile);
                logs.close();
            } else {
                logFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("no file created " + e);
        }
        this.path = path;
        try {
            this.itemList = new ArrayList<>(Files.readAllLines(Paths.get(path)));
        } catch (IOException e) {
            System.out.println(e);
            this.itemList = new ArrayList<>();
        }
    }

    /**
     * Adds a task to the underlying file storage.
     * @param task Task to be added
     */
    public void add(Task task) {
        assert task != null : "Task should not be null. ";
        this.itemList.add(task.toStorageString());
        this.update();
    }

    /**
     * Replaces a line in the storage file with the details of a new Task object.
     * @param index Index of item to be replaced
     * @param newTask New task
     */
    public void set(int index, Task newTask) {
        assert index > 0 : "Index passed to set must be greater than 1. ";
        assert index <= this.itemList.size() : "Index passed to set cannot be greater than length of task list. ";
        assert newTask != null : "newTask cannot be null. ";
        this.itemList.set(index - 1, newTask.toStorageString());
        this.update();
    }

    /**
     * Removes a line from the file storage.
     * @param index Index of line to be removed
     */
    public void remove(int index) {
        assert index > 0 : "Index passed to remove must be greater than 1. ";
        assert index <= this.itemList.size() : "Index passed to remove cannot be greater than length of task list. ";
        this.itemList.remove(index - 1);
        this.update();
    }

    /**
     * Returns all lines in storage as ArrayList of Strings.
     * @return List of lines in storage
     */
    public ArrayList<String> readStorage () {
        return this.itemList;
    }

    /**
     * Updates underlying file storage by overwriting entire file with new contents.
     */
    public void update() {
        try {
            PrintWriter writer = new PrintWriter(path);
            for (String line : this.itemList) {
                writer.println(line);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
