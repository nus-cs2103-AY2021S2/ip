package Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import Duke.*;

public class Storage {
    private ArrayList<String> listOfTasks;
    private String path;

    public Storage (String path) {
        this.path = path;
        try {
            this.listOfTasks = new ArrayList<>(Files.readAllLines(Paths.get(path)));
        } catch (IOException e) {
            System.out.println(e);
            this.listOfTasks = new ArrayList<>();
        }
    }

    public ArrayList<String> load() {
        return this.listOfTasks;
    }

    public void add(Task task) {
        this.listOfTasks.add(task.encode());
        this.update();
    }

    public void remove(int index) {
        this.listOfTasks.remove(index - 1);
        this.update();
    }

    public void set(int index, Task newTask) {
        this.listOfTasks.set(index - 1, newTask.encode());
        this.update();
    }

    public void update() {
        try {
            PrintWriter writer = new PrintWriter(this.path);
            for (String line : this.listOfTasks) {
                writer.println(line);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
