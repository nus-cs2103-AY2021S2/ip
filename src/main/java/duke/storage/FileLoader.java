package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.exceptions.DukeExceptionFileNotAccessible;
import duke.exceptions.DukeExceptionFileNotWritable;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.tasks.TaskList;

public class FileLoader {

    protected File f;
    private boolean isWritable;
    private boolean isReadable;

    /**
     * Loads the string path to the task database.
     *
     * If uninitialized, will attempt to initialize.
     * Checks whether file is readable/writable prior to loading of tasks.
     *
     * @param pathStr Path to database.
     * @throws DukeExceptionFileNotAccessible If file cannot be created/read.
     */
    public FileLoader(String pathStr) throws DukeExceptionFileNotAccessible {
        f = new File(pathStr);
        isReadable = f.canRead();
        isWritable = f.canWrite();
        if (!isReadable) {
            if (!f.getParentFile().mkdirs()) {
                throw new DukeExceptionFileNotAccessible("Unable to create directory to file.");
            }
            try {
                f.createNewFile();
                isReadable = f.canRead();
                isWritable = f.canWrite();
            } catch (IOException e) {
                throw new DukeExceptionFileNotAccessible("Unable to create file.");
            }
        }
        if (!isReadable) {
            throw new DukeExceptionFileNotAccessible("Unable to read file.");
        }
    }

    /**
     * Throws exception if file not writable.
     *
     * Used to indicate that a file can be read, but cannot be written (read-only).
     *
     * @throws DukeExceptionFileNotWritable When file cannot be written to.
     */
    public void throwIfNotWritable() throws DukeExceptionFileNotWritable {
        if (!isWritable) {
            throw new DukeExceptionFileNotWritable("Unable to write to file.");
        }
    }

    /**
     * Writes tasks in tasklist to file.
     *
     * @param t TaskList.
     * @throws DukeExceptionFileNotWritable When file cannot be written to.
     */
    public void write(TaskList t) throws DukeExceptionFileNotWritable {
        if (isWritable) {
            try (FileWriter writer = new FileWriter(f, false)) {
                for (String s: t.asArrayList()) {
                    writer.write(s + '\n');
                }
            } catch (IOException e) {
                throw new DukeExceptionFileNotWritable("Unable to write to file.");
            } catch (DukeExceptionIllegalArgument e) {
                throw new DukeExceptionFileNotWritable("Problem with task.");
            }
        }
    }

    /**
     * Reads tasks using file pointer initialized in constructor.
     *
     * @return TaskList.
     * @throws DukeExceptionIllegalArgument When file cannot be read/loaded.
     */
    public TaskList read() throws DukeExceptionFileNotAccessible, DukeExceptionIllegalArgument {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line;
            ArrayList<String> tasks = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
            return TaskList.fromFileStrings(tasks);
        } catch (IOException e) {
            // Can happen with directory change
            throw new DukeExceptionIllegalArgument("Error in reading file.");
        }
    }
}
