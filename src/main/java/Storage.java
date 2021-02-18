import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Storage {

    /**
     * Hard coded path for file.
     */
    private static final Path fileLocation = Paths.get("./data", "duke.txt");
    /**
     * Hard coded path for directory.
     */
    private static final Path directoryLocation = Paths.get("./data");
    /**
     * Data retrieved from file.
     */
    private static ArrayList<Task> data;

    private static void createFile() {
        try {
            Files.createFile(fileLocation);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static boolean checkForFile() {
        boolean fileExists = Files.exists(fileLocation);
        return fileExists;
    }

    private static boolean checkForDirectory() {
        boolean directoryExists = Files.exists(directoryLocation);
        return directoryExists;
    }

    private static void createDirectory() {
        try {
            Files.createDirectory(directoryLocation);
            System.out.println("directory created");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private static void convertDataToTask(List<Task> tasks, String task) {
        String typeOfTask = Parser.parseTypeOfTask(task);
        String isDone = Parser.parseCompletionStatus(task);
        String name = Parser.parseName(task);
        LocalDateTime time = Parser.parseTime(task);
        if (typeOfTask.equals("T")) {
            tasks.add(new Todo(name));
            if (isDone.equals("1")) {
                tasks.get(tasks.size() - 1).isDone = true;
            }
        }
        if (typeOfTask.equals("D")) {
            tasks.add(new Deadline(name, time));
            if (isDone.equals("1")) {
                tasks.get(tasks.size() - 1).isDone = true;
            }
        }
        if (typeOfTask.equals("E")) {
            tasks.add(new Event(name, time));
            if (isDone.equals("1")) {
                tasks.get(tasks.size() - 1).isDone = true;
            }
        }
    }

    /**
     * Reads the data file and creates tasks.
     *
     * @return ArrayList consisting of all saved tasks.
     * @throws UnableToLoadDataException If unable to load data from the file.
     */
    public static ArrayList<Task> getDataFromFile() throws UnableToLoadDataException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Files.lines(fileLocation)
                    .map(task -> task.trim())
                    .filter(task -> !task.isEmpty())
                    .forEach(task -> {
                        convertDataToTask(tasks, task);
                    });
        } catch (IOException e) {
            throw new UnableToLoadDataException();
        }
        return tasks;
    }

    /**
     * Saves tasks input by the user on the data file.
     *
     * @param tasks Tasks to be saved on the file.
     * @throws UnableToSaveDataException If unable to save tasks to the file.
     */
    public static void saveDataToFile(List<Task> tasks) throws UnableToSaveDataException {
        try {
            BufferedWriter writer = Files.newBufferedWriter(fileLocation, StandardCharsets.UTF_8);
            for (Task task : tasks) {
                writer.write(task.getSaveString());
                writer.write("\n");
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            throw new UnableToSaveDataException();
        }
    }

    /**
     * Returns an ArrayList containing all the tasks retrieved from storage.
     * Creates data file if it doesn't exist.
     *
     * @return ArrayList of loaded tasks.
     */
    public static ArrayList<Task> getData() {
        boolean directoryExists = checkForDirectory();
        if (!directoryExists) {
            createDirectory();
        }
        boolean fileExists = checkForFile();
        if (!fileExists) {
            createFile();
            data = new ArrayList<>();
        } else {
            try {
                data = getDataFromFile();
            } catch (UnableToLoadDataException er) {
                Ui.getUnableToLoadDataMessage();
            }
        }
        return data;
    }
}
