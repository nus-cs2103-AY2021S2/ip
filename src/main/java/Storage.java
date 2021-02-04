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

    public Storage (String path) {
        this.path = path;
        try {
            this.itemList = new ArrayList<>(Files.readAllLines(Paths.get(path)));
        } catch (IOException e) {
            System.out.println(e);
            this.itemList = new ArrayList<>();
        }
    }
    public void add(Task task) {
        this.itemList.add(task.toStorageString());
        this.update();
    }
    public void set(int index, Task newTask) {
        this.itemList.set(index - 1, newTask.toStorageString());
        this.update();
    }
    public void remove(int index) {
        this.itemList.remove(index - 1);
        this.update();
    }

    public ArrayList<String> readStorage () {
        return this.itemList;
    }
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
