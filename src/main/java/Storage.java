import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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
        this.itemList.add(task.toStorageString());
        this.update();
    }

    /**
     * Replaces a line in the storage file with the details of a new Task object.
     * @param index Index of item to be replaced
     * @param newTask New task
     */
    public void set(int index, Task newTask) {
        this.itemList.set(index - 1, newTask.toStorageString());
        this.update();
    }

    /**
     * Removes a line from the file storage.
     * @param index Index of line to be removed
     */
    public void remove(int index) {
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
