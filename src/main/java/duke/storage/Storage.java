package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * Storage class handles the loading and saving of tasks to hard disk
 */
public class Storage {
    private final File file;

    /**
     * Constructor for Storage class. Takes as input the file path where the data will be read/written to
     *
     * @param filePath File path where data will be read/written to
     */
    public Storage(String filePath) {
        this.file = new File(filePath);

        /* Create directory if it does not exist */
        if (!this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdir();
        }
    }

    /**
     * Save tasks to hard disk
     *
     * @param taskList List of tasks to be written to disk
     * @throws DukeException If IOException encountered
     */
    public void save(List<Task> taskList) throws DukeException {
        try {
            FileWriter writer = new FileWriter(this.file);

            for (Task task : taskList) {
                writer.write(task.storageEntry());
                writer.write(System.lineSeparator());
            }

            writer.flush();
        } catch (IOException e) {
            throw new DukeException("Encountered an error when saving duke.tasks to file");
        }
    }

    /**
     * Loads tasks from hard disk
     *
     * @return List of tasks read from hard disk
     * @throws DukeException If FileNotFoundException occurs which is possible in the event that the file is
     *                       missing/not created yet
     */
    public List<Task> load() throws DukeException {
        try {
            Scanner scanner = new Scanner(this.file);
            List<Task> taskList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                taskList.add(Task.parseRecord(data));
            }
            scanner.close();
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("Encountered an error when loading duke.tasks from file");
        }
    }
}
