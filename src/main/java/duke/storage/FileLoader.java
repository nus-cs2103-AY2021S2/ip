package duke.storage;

import duke.exceptions.DukeExceptionFileNotAccessible;
import duke.exceptions.DukeExceptionFileNotWritable;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.tasks.TaskList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader {

    protected File f;
    public boolean isWritable;
    public boolean isReadable;

    // Load file containing task list information
    @SuppressWarnings("ResultOfMethodCallIgnored")
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

    public void throwIfNotWritable() throws DukeExceptionFileNotWritable {
        if (!isWritable) {
            throw new DukeExceptionFileNotWritable("Unable to write to file.");
        }
    }

    public void write(TaskList t) throws DukeExceptionFileNotWritable {
        if (isWritable) {
            try (FileWriter writer = new FileWriter(f, false)){
                for (String s: t.asArrayList()) {
                    writer.write(s+'\n');
                }
            } catch (IOException e) {
                throw new DukeExceptionFileNotWritable("Unable to write to file.");
            }
        }
    }

    public TaskList read() throws DukeExceptionFileNotAccessible, DukeExceptionIllegalArgument {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line;
            ArrayList<String> tasks = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
            return new TaskList(tasks);
        } catch (IOException e) {
            // Can happen with directory change
            throw new DukeExceptionIllegalArgument("Error in reading file.");
        }
    }
}
