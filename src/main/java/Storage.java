import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Duke's Storage. Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    File dukeDataFile;

    /**
     * Class constructor specifying the file path to save the task list to. If the file does not exist,
     * creates the data file. If the directory does not exist, creates the directory, then creates the file inside.
     * @param filePath File path to open or create that will store the data file for the task list.
     */
    public Storage(String filePath) {
        Path path = Paths.get(filePath);
        dukeDataFile = new File(filePath);
        if (!Files.exists(path)) {
            try {
                dukeDataFile.createNewFile();
            } catch (IOException e) {
                try {
                    Path dirPath = Paths.get("data/");
                    Files.createDirectories(dirPath);
                    dukeDataFile.createNewFile();
                } catch (IOException ex) {
                    System.out.println("IOException caught: " + ex.getMessage());
                }
            }
        }
        assert dukeDataFile.exists();
    }

    /**
     * Reads tasks from this storage object's data file and populates
     * the specified TaskList with the read data, if any.
     * @param taskList The specified TaskList object to be written to.
     */
    public void readFromStorage(TaskList taskList) {
        assert dukeDataFile.exists();
        try {
            Scanner fileScanner = new Scanner(dukeDataFile);
            while (fileScanner.hasNext()) {
                String[] taskArgs = fileScanner.nextLine().split(" ", 2);
                switch (taskArgs[0]) {
                case "todo":
                    taskArgs = taskArgs[1].split(" ", 2);
                    Task newToDo = new ToDo(taskArgs[1]);
                    if (taskArgs[0].equals("done")) {
                        newToDo.markAsDone();
                    }
                    taskList.add(newToDo);
                    break;

                case "deadline":
                    taskArgs = taskArgs[1].split(" ", 4);
                    Task newDeadline = new Deadline(taskArgs[3], taskArgs[0] + " " + taskArgs[1]);
                    if (taskArgs[2].equals("done")) {
                        newDeadline.markAsDone();
                    }
                    taskList.add(newDeadline);
                    break;

                case "event":
                    taskArgs = taskArgs[1].split(" ", 4);
                    Task newEvent = new Event(taskArgs[3], taskArgs[0] + " " + taskArgs[1]);
                    if (taskArgs[2].equals("done")) {
                        newEvent.markAsDone();
                    }
                    taskList.add(newEvent);
                    break;

                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /**
     * Writes tasks from the specified TaskList to this storage object's data file, overwriting the current data.
     * @param taskList The specified TaskList object to read from.
     * @throws IOException If writing to the file fails.
     */
    public void writeToStorage(TaskList taskList) throws IOException {
        assert dukeDataFile.exists();
        FileWriter fw = new FileWriter(dukeDataFile);
        taskList.tasks.forEach(task -> {
            try {
                fw.write(task.generateDataString() + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("IOException thrown.");
            }
        });
        fw.close();
    }
}
