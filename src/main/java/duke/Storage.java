package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        assert path != null : "storage filepath is null";

        List<Task> tasks = new ArrayList<>();
        Files.createDirectories(path.getParent());
        if (!Files.exists(path)) {
            Files.createFile(path);
            return tasks;
        }
        BufferedReader reader = Files.newBufferedReader(path);
        String taskType = reader.readLine();
        while (taskType != null) {
            Task newTask;
            String taskName = reader.readLine();
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
                assert false : "error in data file";
                return tasks;
            }

            boolean isDone = Boolean.parseBoolean(reader.readLine());
            if (isDone) {
                newTask.markAsDone();
            }
            tasks.add(newTask);
            taskType = reader.readLine();
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
        assert path != null : "storage filepath is null";

        BufferedWriter writer = Files.newBufferedWriter(path);
        for (Task task : tasks) {
            writer.write(task.toString().charAt(1));
            writer.newLine();
            writer.write(task.name);
            writer.newLine();
            writer.write(Boolean.toString(task.isDone));
            writer.newLine();
        }
        writer.flush();
    }
}
