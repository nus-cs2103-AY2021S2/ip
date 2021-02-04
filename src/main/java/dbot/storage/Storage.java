package dbot.storage;

import dbot.command.Command;
import dbot.exception.DBotException;
import dbot.parser.Parser;
import dbot.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The Storage class handles all file reading and writing operations that DBot might have.
 */
public class Storage {
    /** Default file path used if the user does not provide a file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "addressbook.txt";
    public final Path path;

    /**
     * Initializes a default Storage instance that uses the default file path.
     *
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * Initializes a Storage instance for the given file path.
     *
     * @param filePath A String providing the location of the file to read and write data to.
     * @throws InvalidStorageFilePathException If the given file path is invalid.
     */
    public Storage(String filePath) throws InvalidStorageFilePathException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    /**
     * Saves the given TaskList by writing it to a file, creating one if one does not already exist.
     *
     * @param tasks The TaskList to be saved.
     * @throws StorageOperationException If the file cannot be written to.
     */
    public void save(TaskList tasks) throws StorageOperationException {
        try {
            if (Files.notExists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            FileWriter fw = new FileWriter(path.toString());
            fw.write(tasks.encode());
            fw.close();
        } catch (IOException e) {
            throw new StorageOperationException(e);
        }
    }

    /**
     * Loads and returns a TaskList from the file path this storage instance was instantiated with.
     *
     * @return A TaskList containing the Tasks that were read from a file.
     * @throws DBotException If the file cannot be found or if a line in the file cannot be parsed as a
     * command.
     */
    public TaskList load() throws DBotException {
        try {
            File file = new File(path.toString());
            Scanner scanner = new Scanner(file);
            return loadFromScanner(scanner);
        } catch (FileNotFoundException | DBotException e) {
            throw new InvalidStorageFilePathException(e);
        }
    }

    /**
     * Helper function to load a TaskList from a Scanner object.
     *
     * @param scanner A Scanner that provides saved Tasks when iterated over.
     * @return A TaskList containing the Tasks that were read from the scanner.
     * @throws DBotException If a line provided by the Scanner cannot be parsed as a command.
     */
    private TaskList loadFromScanner(Scanner scanner) throws DBotException {
        TaskList tasks = new TaskList();
        while (scanner.hasNext()) {
            Command command = Parser.parseSaved(scanner.nextLine());
            command.quietExecute(tasks, this);
        }
        return tasks;
    }

    /**
     * Returns the Path as a String.
     *
     * @return A String representation of the Path.
     */
    public String getPath() {
        return path.toString();
    }

    /**
     * Returns true if the provided Path is valid and false otherwise.
     * A Path is valid if it ends with '.txt'.
     *
     * @param path The Path to be checked for validity.
     * @return True if the Path is valid and false otherwise.
     */
    private boolean isValidPath(Path path) {
        // Must be a human-editable file
        return path.toString().endsWith(".txt");
    }


    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends DBotException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }

        public InvalidStorageFilePathException(Throwable cause) {
            super(cause);
        }
    }

    /**
     * Signals that some error has occurred while trying to read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends DBotException {
        public StorageOperationException(String message) {
            super(message);
        }

        public StorageOperationException(Throwable cause) {
            super(cause);
        }
    }
}
