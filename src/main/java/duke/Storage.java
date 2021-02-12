package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Responsible for storing and fetching the data of the tasks from the hard disk
 */

public class Storage {

    private String filePath;

    /**
     * Constructor.
     *
     * @param filePath directory path to the file in which to save the tasks or load the tasks from
     */

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * loads all the tasks from a file into a List.
     *
     * @return List of all tasks stored in a file
     * @throws DukeStorageException when unable find the file
     */

    public List<Task> loadStorage() throws DukeStorageException, DukeParseException{
        List<Task> savedListOfTasks = new ArrayList<>();
        File fileSource;
        Scanner scanner;
        try {
            fileSource = new File(filePath);
            scanner = new Scanner(fileSource);
        } catch (FileNotFoundException err) {
            throw new DukeStorageException("Cannot find data.txt file to load from.");
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Task t = Parser.parseTaskFromStoredFormat(line);
            savedListOfTasks.add(t);
        }
        return savedListOfTasks;
    }

    /**
     * saves all the Tasks inside the List to a file on the hard disk.
     *
     * @param listOfTasks list of Tasks to save
     * @throws IOException when there is error reading or writing to the file.
     */

    public void saveTasks(TaskList listOfTasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(""); // clear the file.
        fw.close();
        FileWriter fileWriterToAppend = new FileWriter(filePath, true);
        for (Task t : listOfTasks) {
            fileWriterToAppend.write(t.getSavedStringFormat() + "\n");
        }
        fileWriterToAppend.close();
    }
}
