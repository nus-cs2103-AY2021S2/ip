package duke.storage;

import duke.DukeException;
import duke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);

        /* Create directory if it does not exist */
        if (!this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdir();
        }
    }

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
