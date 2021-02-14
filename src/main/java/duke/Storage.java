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

    private static final String WRITE_FILE_EXCEPTION_MESSAGE =
            "Unable to write to file! Check duke/data.txt file.";
    private static final String READ_FILE_EXCEPTION_MESSAGE =
            "Unable to read the duke/data.txt because it cant be found." ;

    private String filePath;

    /**
     * Constructor.
     *
     * @param filePath directory path to the file in which to save the tasks or load the tasks from
     */

    Storage(String filePath) {
        this.filePath = filePath;
    }


    private Scanner getScannerToReadFile() throws DukeStorageException {
        File fileSource;
        Scanner scanner;
        try {
            fileSource = new File(filePath);
            scanner = new Scanner(fileSource);
        } catch (FileNotFoundException err) {
            throw new DukeStorageException(READ_FILE_EXCEPTION_MESSAGE);
        }
        return scanner;
    }

    public boolean isEmpty(String line) {
        return line.equals("");
    }


    /**
     * loads all the tasks from a file into a List.
     *
     * @return List of all tasks stored in a file
     * @throws DukeStorageException when unable find the file
     */

    public List<Task> loadStorage() throws DukeStorageException, DukeParseException{
        Scanner scanner = getScannerToReadFile();
        List<Task> savedListOfTasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (isEmpty(line)) {
                continue; //consume empty lines
            }
            Task t = StorageParser.parseTaskFromStorageFormat(line);
            savedListOfTasks.add(t);
        }
        return savedListOfTasks;
    }


    private void clearFile() throws DukeStorageException {
        try ( FileWriter fw = new FileWriter(filePath);){
            fw.write(""); // clear the file.
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeStorageException(WRITE_FILE_EXCEPTION_MESSAGE);
        }
    }

    /**
     * saves all the Tasks inside the List to a file on the hard disk.
     *
     * @param listOfTasks list of Tasks to save
     * @throws IOException when there is error reading or writing to the file.
     */

    public void saveTasks(TaskList listOfTasks) throws DukeStorageException {
        clearFile();
        writeTasksToFile(listOfTasks);
    }

    public void writeTasksToFile(TaskList listOfTasks) throws DukeStorageException {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            for (Task t : listOfTasks) {
                fileWriter.write(StorageParser.convertTaskToStorageFormat(t) + "\n");
            }
        } catch( IOException e) {
            e.printStackTrace();
            throw new DukeStorageException(WRITE_FILE_EXCEPTION_MESSAGE);
        }
    }




}
