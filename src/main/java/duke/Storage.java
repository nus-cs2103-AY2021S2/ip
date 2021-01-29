package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the storage functionalities used by
 * the Duke chat bot. It manages the loading and saving
 * of tasks in and out of the disk.
 */
public class Storage {

    protected Path filePath;

    /**
     * Initialises the class with a file path
     * where the file will be used for data storage
     * operations.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(System.getProperty("user.dir"), filePath);
    }

    /**
     * Loads task list data found in the disk
     * into the Duke chat bot.
     *
     * @return Task list.
     * @throws DukeException If the file cannot be found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> collection = new ArrayList<>();

        List<String> lines;
        try {
            lines = Files.readAllLines(this.filePath);
            for (String line : lines) {
                String[] parts = line.replace('|', ' ').split("[ ]+");

                Task task;
                if (parts[0].equals("T"))
                    task = new Todo(parts[2]);
                else if (parts[0].equals("D")) {
                    task = new Deadline(parts[2], LocalDate.parse(parts[3]));
                } else
                    task = new Event(parts[2], LocalDate.parse(parts[3]));

                if (parts[1].equals("1"))
                    task.markAsDone();

                collection.add(task);
            }
        } catch (IOException e) {
            throw new DukeException("Unable to load file.");
        }

        return collection;
    }

    /**
     * Saves the task list data from the
     * Duke chat bot into the disk.
     *
     * @param collection Task list.
     * @return Successful result of the operation.
     * @throws DukeException If the file cannot be found.
     */
    public String save(ArrayList<Task> collection) throws DukeException {
        try {
            Files.createDirectories(this.filePath.getParent()); // force create directories

            StringBuilder sb = new StringBuilder();
            for (Task task : collection) {
                // Task type
                if (task instanceof Todo)
                    sb.append('T');
                else if (task instanceof Deadline)
                    sb.append('D');
                else if (task instanceof Event)
                    sb.append('E');

                // Status
                sb.append(" | ");
                if (task.getStatusIcon().equals("*"))
                    sb.append("1");
                else
                    sb.append("0");

                // Description
                sb.append(" | ");
                sb.append(task.getDescription());

                // Args
                if (task instanceof Deadline) {
                    sb.append(" | ");
                    sb.append(((Deadline)task).getBy());
                } else if (task instanceof Event) {
                    sb.append(" | ");
                    sb.append(((Event)task).getAt());
                }
                sb.append('\n');
            }
            Files.write(this.filePath, sb.toString().getBytes());
            return "Task list saved.";
        } catch (IOException e) {
            throw new DukeException("I don't think there is such a file...");
        }
    }

}
