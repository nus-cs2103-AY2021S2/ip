package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.List;

/**
 * Storage keep tracks of the file that is storing the data.
 * The function of storage is to load data from hard disk and
 * save data to hard disk.
 */
public class Storage {
    private String filePath;

    /**
     * Returns a Storage object that knows where the data file is located.
     *
     * @param filePath The relative path of data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a list of task after reading data in the filepath.
     * Normally, this wil be called at the very start of the program.
     *
     * @return A list of current task in the data file.
     * @throws DukeException If an I/O error occurs.
     */
    public List<Task> load() throws DukeException {
        FileReader fr = new FileReader();

        return fr.readFile(filePath);
    }

    /**
     * Writes the given string to the data file. Overwrites everything currently
     * inside the file.
     *
     * @param modifiedResult String to be write into the data file.
     * @throws DukeException If an I/O error occurs.
     */
    public void save(String modifiedResult) throws DukeException{
        DukeFileWriter fw = new DukeFileWriter();

        fw.writeFile(filePath, modifiedResult);
    }
}
