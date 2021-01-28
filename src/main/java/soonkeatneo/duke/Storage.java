package soonkeatneo.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the opening, writing and opening of files.
 * @author Soon Keat Neo
 * @version CS2103T AY20/21 Sem 1 iP v0.1
 */
public class Storage {
    private String filePath;
    private File dataFile;

    /**
     * Constructs the Storage object with the given file-path.
     * @param filePath File path to be used for the Storage object
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the Storage object and creates paths if non-existent.
     * @return A copy of the Storage object
     */
    public Storage load() {
        this.dataFile = new File(this.filePath);
        this.dataFile.getParentFile().mkdirs();
        if (!dataFile.exists()) {
            try {
                this.dataFile.createNewFile();
            } catch (IOException e) {
                throw new DukeException();
            }
        }
        return this;
    }

    /**
     * Reads the contents of the opened file.
     * @return A String list of contents of the given file.
     */
    public List<String> read() {
        List<String> fileContents;
        try {
            fileContents = Files.readAllLines(dataFile.toPath());
        } catch (IOException e) {
            throw new DukeException();
        }
        return fileContents;
    }

    /**
     * Replaces a line in the file to facilitate deletion and replacement.
     * @param oldString String of the old task.
     * @param newString String of the new task.
     */
    public void deleteReplaceTaskFromDisk(String oldString, String newString) {
        try {
            List<String> fileContents = new ArrayList<>(Files.readAllLines(this.dataFile.toPath()));
            for (int i = 0; i < fileContents.size(); i++) {
                if (fileContents.get(i).equals(oldString)) {
                    fileContents.set(i, newString);
                    break;
                }
            }
            Files.write(this.dataFile.toPath(), fileContents);
        } catch (IOException e) {
            Ui.printMessage(e.getMessage());
        }
    }

    /**
     * Appends a given String to the file.
     * @param string String to be appended to the file
     */
    public void saveTaskToDisk(String string) {
        try {
            FileWriter writer = new FileWriter(this.dataFile, true);
            writer.write(string + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            Ui.printMessage(e.getMessage());
        }
    }
}
