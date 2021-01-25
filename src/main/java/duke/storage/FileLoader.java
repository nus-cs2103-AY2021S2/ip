package duke.storage;

import duke.exceptions.DukeExceptionFileNotAccessible;
import duke.exceptions.DukeExceptionFileNotWritable;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.exceptions.DukeExceptionInvalidTaskString;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;


public class FileLoader {

    protected File f;

    // Load file containing task list information
    public FileLoader(String pathStr) {
        f = new File(pathStr);
    }

    public void write(TaskList t) throws DukeExceptionFileNotWritable {
        try (FileWriter writer = new FileWriter(f, false)){
            for (String s: t.asArrayList()) {
                writer.write(s+'\n');
            }
        } catch (IOException e) {
            throw new DukeExceptionFileNotWritable("Unable to write to file.");
        }
    }

    public TaskList read() throws DukeExceptionFileNotAccessible, DukeExceptionIllegalArgument {
        try {
            // For initialization, does nothing if exists
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (IOException e) {
            throw new DukeExceptionFileNotAccessible("Failed to read file");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line;
            ArrayList<String> tasks = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
            return new TaskList(tasks);
        } catch (IOException e) {
            throw new DukeExceptionIllegalArgument("Error in reading file.");
        }
    }
}
