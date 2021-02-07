package ekud.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ekud.common.exception.EkudException;
import ekud.task.Deadline;
import ekud.task.EventTask;
import ekud.task.Task;
import ekud.task.TaskList;
import ekud.task.ToDo;

/**
 * The file I/O class that handles all file operations
 */
public class Storage {
    private static final String deliminator = "\\|";
    private static final Pattern PARENT_DIRECTORY = Pattern.compile("(?<parentDirectory>^.*/).*$");

    private final String filePath;
    private final File file;
    private boolean canSave;

    /**
     * Construct a new Storage instance that reads and writes to a given file.
     *
     * @param path The path to the file.
     */
    public Storage(String path) {
        filePath = path;
        Matcher matcher = PARENT_DIRECTORY.matcher(filePath);
        if (matcher.matches()) {
            String parentDirectory = (matcher.group("parentDirectory") == null
                    ? "./"
                    : matcher.group("parentDirectory"));
            File folder = new File(parentDirectory);

            if (!folder.exists()) {
                canSave = folder.mkdirs();
            } else {
                canSave = true;
            }
        }

        file = new File(filePath);
    }

    /**
     * Read the file and reconstruct all tasks.
     *
     * @return A Vector containing all reconstructed tasks.
     * @throws EkudException If any errors are encountered during I/O operations, or if the task is invalid.
     */
    public Vector<Task> load() throws EkudException {
        Vector<Task> ret = new Vector<>();
        Scanner s;
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            return ret;
        }

        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] data;
            data = line.split(deliminator, 0);

            switch (data[0]) {
            case "T":
                ret.add(new ToDo(data[2]));
                break;
            case "D":
                ret.add(new Deadline(data[2], LocalDateTime.parse(data[3])));
                break;
            case "E":
                ret.add(new EventTask(data[2], LocalDateTime.parse(data[3])));
                break;
            default:
                throw new EkudException("Unknown task type");
            }

            /* mark as read accordingly */
            if (Boolean.parseBoolean(data[1])) {
                ret.lastElement().markAsDone();
            }
        }

        return ret;
    }

    /**
     * Save the given tasks to the file using specified FileWriter.
     *
     * @param fw    The FileWriter to write to.
     * @param tasks The list of tasks to save.
     * @throws IOException If an I/O error occurs.
     */
    private void writeToFile(FileWriter fw, List<Task> tasks) throws IOException {
        for (Task task : tasks) {
            LinkedList<String> list = task.export();
            fw.write(String.join("|", list) + System.lineSeparator());
        }
    }

    /**
     * Save tasks to disk.
     *
     * @param tasks The list of tasks.
     * @throws EkudException If an I/O error occurs.
     */
    public void save(final TaskList tasks) throws EkudException {
        if (!canSave) {
            return;
        }

        List<Task> list = tasks.export();
        try (FileWriter fw = new FileWriter(filePath, false)) {
            writeToFile(fw, list);
        } catch (IOException e) {
            throw new EkudException("Storage: IOException encountered");
        }
    }
}
