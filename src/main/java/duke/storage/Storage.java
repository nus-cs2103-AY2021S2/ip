package duke.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {
    private final Path dirPath;
    private final Path filePath;

    public Storage(String path) {
        filePath = Path.of(path).toAbsolutePath();
        dirPath = filePath.getParent();
    }

    public String load() throws StorageException {
        try {
            return Files.readString(filePath);
        } catch (IOException ex) {
            throw new StorageException(String.format("Failed to load from %s", filePath.toString()));
        }
    }

    public void save(String data) throws StorageException {
        // Solution below adapted from https://tinyurl.com/y35nn2nl
        // create folders if necessary
        File directory = new File(dirPath.toString());
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // open/create file
        File file = new File(filePath.toString());
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            throw new StorageException(String.format("Failed to save to %s", filePath.toString()));
        }
    }
}
