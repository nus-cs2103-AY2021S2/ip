package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskException;

public class Storage {
    private static final String LIST_FILE_PATH = "storage/";
    private static final String LIST_FILE = LIST_FILE_PATH + "list.txt";

    /**
     * Creates the storage file directory in filesystem if it does not exist.
     * @throws DukeException when fail to create directory.
     */
    public Storage() throws DukeException {
        //Create directory if not exist
        Path path = Paths.get(LIST_FILE_PATH);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new DukeException("Failed to create storage directory. " + e.getMessage());
        }
    }

    /**
     * Returns a FileWriter object to allow for writing to file. If clearFile is not selected, FileWriter object
     * will be in append mode so that file will not be overwritten.
     * @param clearFile determines whether to overwrite file.
     * @return FileWriter object to write to file.
     * @throws DukeException if failed to access the file.
     */
    private FileWriter getFileWriter(boolean clearFile) throws DukeException {
        try {
            File file = new File(LIST_FILE);
            FileWriter fw;
            //If file already exists and we are not overwriting it
            if (file.exists() && !clearFile) {
                fw = new FileWriter(LIST_FILE, true);
            } else {
                fw = new FileWriter(LIST_FILE);
            }
            return fw;
        } catch (IOException e) {
            throw new DukeException("Failed to access file. " + e.getMessage());
        }
    }

    private FileWriter getFileWriter() throws DukeException {
        return getFileWriter(false);
    }

    /**
     * Reads tasks line by line from the storage and stores and returns new Task object in an ArrayList
     * after parsing the lines.
     * @return an ArrayList of task objects of tasks that are read from the storage.
     * @throws DukeException if a task from storage is incorrectly formatted or failed to read storage file.
     */
    public ArrayList<Task> readTasksFromFile() throws DukeException {
        File tasks = new File(LIST_FILE);
        Scanner s;
        ArrayList<Task> newList = new ArrayList<>();
        try {
            s = new Scanner(tasks);
            while (s.hasNext()) {
                newList.add(Parser.stringToTask(s.nextLine()));
            }
            s.close();
            return newList;
        } catch (TaskException | FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Appends the task to the end of the storage file.
     * @param task Task to be appended.
     * @throws DukeException if failed to write task to storage.
     */
    public void writeTaskToFile(Task task) throws DukeException {
        FileWriter fw = getFileWriter();
        try {
            fw.write(task.toString());
            fw.append(System.getProperty("line.separator"));
            fw.close();
        } catch (IOException | NullPointerException e) {
            throw new DukeException("Error writing task to storage/list.txt " + e.getMessage());
        }
    }

    /**
     * Clears the storage file of its contents.
     * @throws DukeException if failed to read or write to storage file..
     */
    public void clearFile() throws DukeException {
        FileWriter fw = getFileWriter(true);
        try {
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to clear file of contents. " + e.getMessage());
        }
    }
}
