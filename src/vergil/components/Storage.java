package vergil.components;

import vergil.types.exceptions.VergilException;
import vergil.types.exceptions.VergilFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a File object with the path filePath.
     * @return a File object representation of filePath.
     */
    public File load() {
        return new File(filePath);
    }

    /**
     * Writes the content of the given task list to filePath.
     * @param tl the task list whose contents are to be written to filePath.
     * @throws VergilException if the FileWriter object fails to write to
     * the specified path.
     */
    public void save(TaskList tl) throws VergilException {
        try {
            FileWriter fw = new FileWriter(filePath);
            Scanner tlEntries = new Scanner(tl.toSaveString());

            while (tlEntries.hasNextLine()) {
                fw.write(tlEntries.nextLine() + System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            throw new VergilFileException("Unable to save.");
        }
    }
}
