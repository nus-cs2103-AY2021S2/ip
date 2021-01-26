package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    private static final String ROOT_PROJECT = System.getProperty("user.dir");
    public static Path saveFilePath =
            Paths.get(ROOT_PROJECT,"src", "data", "duke.txt");
    private static Path saveFolderPath =
            Paths.get(ROOT_PROJECT,"src", "data");

    public Storage(Path filePath) throws DukeException {
        createDbIfNotFound();
    }

    public static Path getSaveFilePath() {
        return saveFilePath;
    }

    protected static void createDbIfNotFound() throws DukeException {
        try {
            if (Files.notExists(saveFilePath)) {
                if (Files.notExists(saveFolderPath)) {
                    saveFolderPath = Files.createDirectories(saveFolderPath);
                }
                saveFilePath = Files.createFile(saveFilePath);
            }
        } catch (IOException e) {
            throw new DukeException("Unable to access data stored, access rights issue."
                    + e);
        }
    }

    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> ledger = new ArrayList<>(100);
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(saveFilePath);
            String record = bufferedReader.readLine();
            while(record != null) {
                ledger.add(stringToTask(record));
                record = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new DukeException("Fail to load data. " + e);
        }
        return ledger;
    }

    public void saveData(ArrayList<Task> tasks) throws DukeException {
        try {
            ArrayList<String> ledger = new ArrayList<>();
            for(Task t : tasks) {
                String s = t.getTaskDetails();
                ledger.add(s);
            }
            Files.write(saveFilePath, ledger);
        } catch (IOException e) {
            throw new DukeException("Fail to save data. " + e);
        }
    }

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
            output = new DeadlineTask(description, isDone, byDate, byTime);
            break;
        case "E":
            String at = savedRecord[3].strip();
            LocalDate atDate = LocalDate.parse(at);
            String atTime = savedRecord[4].strip();
            output = new EventTask(description, isDone, atDate, atTime);
            break;
        default:
            throw new DukeException("Unexpected value: " + taskType);
        }
        return output;
    }
}
