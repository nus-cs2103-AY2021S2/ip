package main.java.duke;

import java.util.List;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Handles saving and loading task list using hard disk storage.
 */
public class Storage {
    protected final Path path;

    /**
     * Initialises storage using a preset file path.
     */
    public Storage() {
        this.path = Paths.get("data/TaskList.txt");
    }

    /**
     * Loads the task list from pre-made text file.
     * Creates the text file if it does not exist.
     *
     * @return List of tasks.
     * @throws IOException If unable to load from file.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        Files.createDirectories(this.path.getParent());
        if (!Files.exists(this.path)) {
            Files.createFile(this.path);
            return tasks;
        }
        BufferedReader br = Files.newBufferedReader(this.path);
        String taskType = br.readLine();
        while (taskType != null) {
            Task newTask;
            String taskName = br.readLine();
            switch (taskType) {
            case "T": {
                newTask = new Todo(taskName);
                break;
            }
            case "D": {
                newTask = new Deadline(taskName);
                break;
            }
            case "E": {
                newTask = new Event(taskName);
                break;
            }
            default:
                return tasks;
            }
            boolean isDone = Boolean.parseBoolean(br.readLine());
            if (isDone) {
                newTask.markAsDone();
            }
            tasks.add(newTask);
            taskType = br.readLine();
        }
        return tasks;
    }

    /**
     * Saves the task list into existing text file.
     *
     * @param tasks List of tasks.
     * @throws IOException If unable to save to file.
     */
    public void save(List<Task> tasks) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(this.path);
        for (Task task : tasks) {
            bw.write(task.toString().charAt(1));
            bw.newLine();
            bw.write(task.name);
            bw.newLine();
            bw.write(Boolean.toString(task.isDone));
            bw.newLine();
        }
        bw.flush();
    }
}
