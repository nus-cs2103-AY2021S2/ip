package Duke.Helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Task;
import Duke.Task.Todo;

/**
 * A class handles tasks on saving tasks to file and loading tasks from file.
 */
public class Storage {
    private final String path;
    private List<String> taskFile;

    /**
     * This class constructor has 1 parameter: a string path to the destination file for reading and writing the data.
     * @param path The destination file for reading and writing the data.
     */
    public Storage(String path) {
        this.path = path;
        Path p = Path.of(path);
        Path parent = p.getParent();
        try {
            Files.createDirectories(parent);
            if (!Files.exists(p)) {
                Files.createFile(p);
            }
            taskFile = Files.readAllLines(p);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads the data from the file when the application starts running.
     * @return A list containing all the tasks stored in the file.
     */
    public ArrayList<Task> readDataFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String currLine : taskFile) {
            String[] information = currLine.split("\\|");
            if (information[0].charAt(0) == 'T') {
                Task newTask = new Todo(information[2]);
                if (information[1].trim().equals("1")) {
                    newTask.markAsDone();
                }
                tasks.add(newTask);
            } else if (information[0].charAt(0) == 'D') {
                Task newTask = new Deadline(information[2], information[3]);
                if (information[1].trim().equals("1")) {
                    newTask.markAsDone();
                }
                tasks.add(newTask);
            } else if (information[0].charAt(0) == 'E') {
                Task newTask = new Event(information[2], information[3]);
                if (information[1].trim().equals("1")) {
                    newTask.markAsDone();
                }
                tasks.add(newTask);
            }
        }
        return tasks;
    }

    /**
     * Saves the data to the file after every update (adding or deleting a task)
     * @param list A list containing all the tasks that needs to be saved.
     */
    public void writeDataToFile(ArrayList<Task> list) {
        try {
            File f = new File(path);
            FileWriter fw = new FileWriter(f);
            for (Task task : list) {
                String content = task.getType().trim() + " | " + task.getStatusNumber() + " | "
                        + task.getDescription().trim() + task.getTime().trim();
                fw.write(content + '\n');
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file " + e.getMessage());
        }
    }
}
