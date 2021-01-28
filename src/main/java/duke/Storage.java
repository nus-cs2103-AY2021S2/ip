package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;
    private File dataFile;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
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
