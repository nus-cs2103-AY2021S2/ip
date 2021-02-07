package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Storage {
    private static final String ROOT_PROJECT = System.getProperty("user.dir");
    private static Path saveFilePath =
            Paths.get(ROOT_PROJECT, "src", "data", "duke.txt");
    private static Path saveFolderPath =
            Paths.get(ROOT_PROJECT, "src", "data");

    /**
     * Constructs a Storage object.
     * @param filePath Path of file
     * @throws DukeException If database cannot be created or found.
     */
    public Storage(Path filePath) throws DukeException {
        createDbIfNotFound();
    }

    /**
     * Returns the path for the address to save the file.
     * @return Path
     */
    public static Path getSaveFilePath() {
        return saveFilePath;
    }

    /**
     * Creates a file w/wo directory for storing of file.
     * @throws DukeException If access rights issue is violated.
     */
    protected static void createDbIfNotFound() throws DukeException {
        try {
            if (Files.notExists(saveFilePath)) {
                if (Files.notExists(saveFolderPath)) {
                    saveFolderPath = Files.createDirectories(saveFolderPath);
                }
                saveFilePath = Files.createFile(saveFilePath);
            }
        } catch (IOException e) {
            throw new DukeException("Unable to access data stored."
                    + e);
        }
    }

    /**
     * Returns an ArrayList of Tasks by attempting to load that information from a stored file.
     * @return An ArrayList of Tasks
     * @throws DukeException If data could not be loaded.
     */
    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> ledger = new ArrayList<>();
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(saveFilePath);
            String record = bufferedReader.readLine();
            while (record != null) {
                ledger.add(stringToTask(record));
                record = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new DukeException("Fail to load data. " + e);
        }
        return ledger;
    }

    /**
     * Saves the list of Tasks into a file to be recovered the
     * next time the user needs it.
     * @param tasks An ArrayList of Tasks
     * @throws DukeException If data could not be saved.
     */
    public void saveData(ArrayList<Task> tasks) throws DukeException {
        try {
            ArrayList<String> ledger = new ArrayList<>();
            for (Task t : tasks) {
                String s = t.getTaskDetails();
                ledger.add(s);
            }
            Files.write(saveFilePath, ledger);
        } catch (IOException e) {
            throw new DukeException("Fail to save data. " + e);
        }
    }

    /**
     * Returns a Task which is converted from an input String.
     * @param taskInfo String containing information about the Task
     * @return Task
     * @throws DukeException If task could not be created successfully due to input error
     */
    private static Task stringToTask(String taskInfo) throws DukeException {
        String[] savedRecord = taskInfo.split("\\|");
        Task output;
        String taskType = savedRecord[0].strip();
        boolean isDone = savedRecord[1].strip().equals("1");
        String description = savedRecord[2].strip();
        switch(taskType) {
        case "T":
            output = new ToDoTask(description, isDone);
            break;
        case "D":
            String by = savedRecord[3].strip();
            LocalDate byDate = LocalDate.parse(by);
            String byTime = savedRecord[4].strip();
            LocalTime byLocalTime = LocalTime.parse(byTime);
            output = new DeadlineTask(description, isDone, byDate, byLocalTime);
            break;
        case "E":
            String at = savedRecord[3].strip();
            LocalDate atDate = LocalDate.parse(at);
            String atTime = savedRecord[4].strip();
            LocalTime atLocalTime = LocalTime.parse(atTime);
            output = new EventTask(description, isDone, atDate, atLocalTime);
            break;
        default:
            throw new DukeException("Unexpected value: " + taskType);
        }
        return output;
    }
}
