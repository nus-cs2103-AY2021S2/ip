package duke.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Abstracts saving and loading a string to and from a file.
 *
 * @author Benedict Khoo
 */
public class Storage {
    private final Path dirPath;
    private final Path filePath;

    /**
     * Constructs a Storage with the given file path.
     *
     * @param path The file path to use for saving and loading.
     */
    public Storage(String path) {
        filePath = Path.of(path).toAbsolutePath();
        dirPath = filePath.getParent();
    }

    /**
     * Attempts to load text from the configured file path. Throws a StorageException if it fails to load.
     *
     * @return The text read from the configured file path.
     * @throws StorageException If it fails to load.
     */
    public String load() throws StorageException {
        try {
            return Files.readString(filePath);
        } catch (IOException ex) {
            throw new StorageException(String.format("Failed to load from %s", filePath.toString()));
        }
    }

    /**
     * Attempts to save text to the configured file path. Throws a StorageException if it fails to save.
     * If the configured file path specifies non-existent folders or a non-existent file, this method
     * attempts to create them.
     *
     * @param data The text to be saved.
     * @throws StorageException If it fails to save.
     */
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
