package duke.interaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import duke.common.DukeException;
import duke.task.TaskList;

/**
 * A class that handles the read and write of Tasks to disk.
 */
public class Storage {
    private final File saveFile;

    /**
     * Construct a new Storage object with the associated save path.
     *
     * Does not attempt to create file until a write is called.
     * @param filePath the path of the file to be used for persistent storage.
     */
    public Storage(String filePath) {
        this.saveFile = new File(filePath);
    }

    /**
     * Read the tasks from the given save file, if it exists and can be read. Otherwise, return an empty TaskList.
     *
     * If the file is malformed, create a backup of the file before proceeding with empty TaskList.
     * @return the TaskList as read from the save file, if it exists and can be read. Otherwise, return empty TaskList.
     */
    public TaskList readTasks() {
        try (Scanner sc = new Scanner(saveFile)) {
            return TaskList.deserialise(sc);
        } catch (DukeException.StorageReadError e) {
            System.err.println(e);

            try {
                Files.copy(saveFile.toPath(), Path.of("data/duke.bak"), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                System.err.println(ex);
            }

            return new TaskList();
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }

    /**
     * Writes the given TaskList out to file, if valid. Otherwise, creates a new file.
     *
     * @param taskList the task list to be written out.
     */
    public void writeTasks(TaskList taskList) {
        this.saveFile.getParentFile().mkdirs();
        assert this.saveFile.getParentFile() != null;

        try {
            this.saveFile.createNewFile();
            assert this.saveFile != null;

            FileWriter fw = new FileWriter(saveFile);
            fw.write(taskList.serialise());
            fw.close();
        } catch (IOException e) {
            throw new DukeException.StorageWriteError();
        }

    }
}
