package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class manages loading tasks from a file
 * when a new instance of Duke is created and saving tasks
 * to a file when a running instance of Duke is closed.
 */
public class Storage {
    private File file;

    /**
     * Constructs a new instance of a Storage object.
     * The storage object will attempt to read and write to a file.
     * @param pathname The pathname of the file.
     */
    public Storage(String pathname) {
        this.file = new File(pathname);
    }

    /**
     * Attempts to load tasks from a file associated
     * with an instance of the Storage object and
     * returns a task list representation of that file.
     * @return The task list saved to the file.
     * @throws DukeException Error trying to load tasks from the file.
     */
    public List<Task> loadTasksFromFile() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try {
            if (!this.file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
                return tasks;
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskData = line.split("\\|");
                Task task = null;

                switch(taskData[0]) {
                case("T"):
                    task = new Todo(taskData[2]);
                    break;
                case("D"):
                    task = new Deadline(taskData[2], taskData[3]);
                    break;
                case("E"):
                    task = new Event(taskData[2], taskData[3]);
                    break;
                }

                if (task != null && taskData[1].equals("1")) {
                    task.setDone(true);
                }

                tasks.add(task);
            }

        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return tasks;
    }

    /**
     * Attempts to save a list of tasks to a file
     * associated with an instance of the Storage object.
     * @param tasks The list of tasks to be saved.
     * @throws DukeException Error trying to save tasks to the file.
     */
    public void saveTasksToFile(TaskList tasks) throws DukeException {
        StringBuilder taskString = new StringBuilder();
        tasks.forEach(task -> taskString.append(task.toSaveFormat()));

        try {
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {
            writer.write(taskString.toString());
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
