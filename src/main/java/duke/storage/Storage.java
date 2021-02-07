package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeCorruptedStorageException;
import duke.exceptions.DukeCreateDirectoryException;
import duke.exceptions.DukeCreateFileException;
import duke.exceptions.DukeSaveFileException;
import duke.tasks.Task;

/**
 * Represents the Storage file that is used to store and update the save file.
 */
public class Storage {
    private static final String DATA_DIR = new File("data").getAbsolutePath();
    private static Storage storage;
    private final File saveFile = new File(DATA_DIR + "/save.txt");

    private Storage() throws DukeCreateDirectoryException {
        createDirectory();
    }

    /**
     * Returns a new Storage or an existing one.
     * @return a new or existing Storage class.
     */
    public static Storage getInstance() throws DukeCreateDirectoryException {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    /**
     * Updates the save file in the hardware with the new TaskList.
     * @param tasks The TaskList used to update the save file.
     */
    public void update(ArrayList<Task> tasks) throws DukeSaveFileException {
        try {
            FileWriter fw = new FileWriter(saveFile);
            fw.write(StorageEncoder.encodeTasks(tasks));
            fw.close();
        } catch (IOException e) {
            throw new DukeSaveFileException();
        }
    }

    /**
     * Returns an ArrayList of Task from the save file.
     * @return an ArrayList of Task from the save file.
     */
    public ArrayList<Task> load() throws DukeCreateFileException, DukeCorruptedStorageException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!saveFile.createNewFile()) {
                Scanner sc = new Scanner(saveFile);
                ArrayList<String> inputs = new ArrayList<>();
                while (sc.hasNext()) {
                    inputs.add(sc.nextLine());
                }
                tasks = StorageDecoder.decodeSave(inputs);
            }
        } catch (IOException e) {
            throw new DukeCreateFileException();
        }
        return tasks;
    }

    /**
     * Creates a data directory from source unless it already exists.
     */
    private void createDirectory() throws DukeCreateDirectoryException {
        Path dataPath = Paths.get(DATA_DIR);
        try {
            Files.createDirectories(dataPath);
        } catch (IOException e) {
            throw new DukeCreateDirectoryException(DATA_DIR);
        }
    }
}
