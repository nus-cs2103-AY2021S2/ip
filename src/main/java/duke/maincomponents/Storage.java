package duke.maincomponents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.exceptions.TaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Storage class, which stores all the Tasks to memory
 */
public class Storage {
    private String pathOfDataFile;

    /**
     * Default constructor for Storage
     *
     * @param filePath path of file to load and store
     * @param folderName foldername of where the file resides
     * @throws DukeException
     */
    public Storage(String filePath, String folderName) throws DukeException {
        pathOfDataFile = filePath;
        try {
            File folder = new File(folderName);
            folder.mkdir();

            File dataFile = new File(filePath);
            dataFile.createNewFile();

        } catch (IOException e) {
            throw new DukeException("Error occured during file or folder creation");
        }
    }

    /**
     * Loads memory into duke
     *
     * @return Array List of tasks
     * @throws DukeException IO exception if file cannot be read
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File dukeData = new File(pathOfDataFile);
            Scanner dataReader = new Scanner(dukeData);
            int tempNumberOfTasks = 0;
            ArrayList<Task> tempArray = new ArrayList<>();

            while (dataReader.hasNextLine()) {
                String data = dataReader.nextLine();
                String[] arr = data.split("\\|");

                String taskSymbol = arr[0];
                int doneInt = Integer.valueOf(arr[1]);
                String description = arr[2];

                if (taskSymbol.equals("T")) {
                    tempArray.add(new ToDo(description, doneInt));
                    tempNumberOfTasks++;
                } else if (taskSymbol.equals("D")) {
                    try {
                        tempArray.add(new Deadline(description, arr[3], doneInt));
                    } catch (TaskException e) {
                        throw new DukeException("Data Corrupted, failed to restore data.");
                    }
                    tempNumberOfTasks++;
                } else if (taskSymbol.equals("E")) {
                    tempArray.add(new Event(description, arr[3], doneInt));
                    tempNumberOfTasks++;
                }
            }
            dataReader.close();

            return tempArray;
        } catch (FileNotFoundException e) {
            throw new DukeException(pathOfDataFile + " not found, failed to restore data.");
        }
    }

    /**
     * Saves current task list to file at every iteration of user input
     *
     * @param taskArray Task array to save to memory
     * @throws DukeException IO exception if an error occurs with the file
     */
    public void saveToFile(ArrayList<Task> taskArray) throws DukeException {
        try {
            Writer fileWriter = new FileWriter("data/dukedata.txt", false);
            for (int i = 0; i < taskArray.size(); i++) {
                String line = taskArray.get(i).toSaveFormat();
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error could not save task list.");
        }
    }
}
