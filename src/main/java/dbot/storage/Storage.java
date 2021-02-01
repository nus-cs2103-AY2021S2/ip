package dbot.storage;

import dbot.parser.Parser;
import dbot.command.Command;
import dbot.exception.DukeException;
import dbot.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "addressbook.txt";
    public final Path path;

    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public Storage(String filePath) throws InvalidStorageFilePathException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    public void save(TaskList tasks) throws StorageOperationException {
        try {
            FileWriter fw = new FileWriter(path.toString());
            fw.write(tasks.encode());
            fw.close();
        } catch (IOException e) {
            throw new StorageOperationException(e);
        }
    }

    public TaskList load() throws DukeException {
        try {
            File file = new File(path.toString());
            Scanner scanner = new Scanner(file);
            return loadFromScanner(scanner);
        } catch (FileNotFoundException | DukeException e) {
            throw new InvalidStorageFilePathException(e);
        }
    }

    private TaskList loadFromScanner(Scanner scanner) throws DukeException {
        TaskList tasks = new TaskList();
        while (scanner.hasNext()) {
            Command command = Parser.parseSaved(scanner.nextLine());
            command.quietExecute(tasks, this);
        }
        return tasks;
    }

    public String getPath() {
        return path.toString();
    }

    private boolean isValidPath(Path path) {
        // Must be a human-editable file
        return path.toString().endsWith(".txt");
    }


    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends DukeException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }

        public InvalidStorageFilePathException(Throwable cause) {
            super(cause);
        }
    }

    /**
     * Signals that some error has occurred while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends DukeException {
        public StorageOperationException(String message) {
            super(message);
        }

        public StorageOperationException(Throwable cause) {
            super(cause);
        }
    }
}
