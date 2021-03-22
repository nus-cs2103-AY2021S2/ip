package storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.Task;

public class Storage {
    private ArrayList<String> listOfTasks;
    private String path;

    /**
     * Storage constructor which creates a Storage object with a filepath.
     * @param path
     */
    public Storage (String path) {
        this.path = path;
        try {
            this.listOfTasks = new ArrayList<>(Files.readAllLines(Paths.get(path)));
        } catch (IOException e) {
            System.out.println(e);
            this.listOfTasks = new ArrayList<>();
        }
    }

    /**
     * A load method which creates an arraylist string of tasks.
     * @return A list of tasks in string format.
     */
    public ArrayList<String> load() {
        return this.listOfTasks;
    }

    /**
     * An add method which adds task to current list of tasks.
     * @param task Task to be added to the list.
     */
    public void add(Task task) {
        this.listOfTasks.add(task.encode());
        this.update();
    }

    /**
     * A remove method which removes a task from current list of task.
     * @param index Index specifying the task to be removed from the arraylist.
     */
    public void remove(int index) {
        this.listOfTasks.remove(index - 1);
        this.update();
    }

    /**
     * A set method which sets a task to a specific index of current task list.
     * @param index Index specifying the position of task to be set.
     * @param newTask The new task that replaces the original task at the index position of arraylist.
     */
    public void set(int index, Task newTask) {
        this.listOfTasks.set(index - 1, newTask.encode());
        this.update();
    }

    /**
     * An update method which updates the list of task in the stored file.
     */
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
