package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * A Storage class to handle storage operations.
 */
public class Storage {
    private final File file;

    /**
     * Constructs Storage.
     *
     * @param path The filepath of where the data file is to be stored at.
     */
    public Storage(String path) {
        this.file = new File(path);
    }

    /**
     * Creates the data file and directory which contains it if it does not exists.
     *
     * @throws DukeException If unable to create file/directory.
     */
    public void createFileAndDirectory() throws DukeException {
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                if (dir.mkdir()) {
                    System.out.println("File directory created.");
                }
            }
            File file = new File("data/tasks.txt");
            if (file.createNewFile()) {
                System.out.println("Data file created.\n");
            } else {
                System.out.println("There is an existing data file.\n");
            }
        } catch (IOException ex) {
            throw new DukeException("Unable to create new file.");
        }
    }

    /**
     * Loads tasks from data file to Duke.
     *
     * @return The list of tasks to be loaded.
     * @throws DukeException If file cannot be found.
     */
    public ArrayList<Task> load() throws DukeException {
        Scanner sc;
        try {
            sc = new Scanner(this.file);
        } catch (FileNotFoundException ex) {
            throw new DukeException("File not found.");
        }
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] currTask = sc.nextLine().split(" // ");
            String type = currTask[0];
            switch (type) {
            case "T":
                tasks.add(new Todo(Integer.parseInt(currTask[1]), currTask[2]));
                break;
            case "D":
                tasks.add(new Deadline(Integer.parseInt(currTask[1]),
                        currTask[2], LocalDate.parse(currTask[3])));
                break;
            case "E":
                tasks.add(new Event(Integer.parseInt(currTask[1]),
                        currTask[2], LocalDate.parse(currTask[3])));
                break;
            default:
            }
        }
        return tasks;
    }

    /**
     * Saves the list of task to the data file.
     *
     * @param tasks The list of tasks to be stored.
     * @throws DukeException If unable to save data.
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file, true);
            for (Task currTask : tasks) {
                fw.write(currTask.getFileString());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException ex) {
            throw new DukeException("       OOPS!!! Unable to save data.");
        }
    }

    /**
     * Clears the data in the existing data file.
     *
     * @throws DukeException If unable to clear data.
     */
    public void clear() throws DukeException {
        try {
            FileWriter cleaner = new FileWriter(file, false);
            cleaner.close();
        } catch (IOException ex) {
            throw new DukeException("       OOPS!!! Unable to clear data.");
        }
    }
}
