package vergil.components;

import vergil.types.VergilException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public File load() {
        return new File(filePath);
    }

    public void save(TaskList tl) throws VergilException {
        try {
            FileWriter fw = new FileWriter(filePath);
            Scanner tlEntries = new Scanner(tl.toSaveString());

            while (tlEntries.hasNextLine()) {
                fw.write(tlEntries.nextLine() + System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            throw new VergilException("Unable to save.");
        }
    }
}
