package flamingo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads and saves data.
 */
public class Storage {
    protected boolean isPathToDataExists;
    protected boolean isPathToArchiveExists;
    private final String currentDirectory;
    private final Path pathToData;
    private final Path pathToArchive;

    /**
     * Creates a new Storage.
     */
    public Storage() {
        this.currentDirectory = System.getProperty("user.dir");
        this.pathToData = java.nio.file.Paths.get(currentDirectory, "data.txt");
        this.pathToArchive = java.nio.file.Paths.get(currentDirectory, "archive.txt");

        this.isPathToDataExists = java.nio.file.Files.exists(pathToData);
        this.isPathToArchiveExists = java.nio.file.Files.exists(pathToArchive);
    }

    public boolean getPathToDataExists() {
        return isPathToDataExists;
    }

    /**
     * Transfers contents from tasks ArrayList to data.txt file.
     *
     * @param tasks TaskList of tasks.
     * @throws IOException If data cannot be saved into file.
     */
    public void saveData(TaskList tasks) {
        try {
            if (isPathToDataExists) {
                overwriteExistingFile(pathToData);
            }
            writeToData(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Transfers contents from archivedTasks ArrayList to archive.txt file.
     *
     * @param tasks ArrayList of archived tasks.
     * @throws IOException If data cannot be saved into file.
     */
    public void saveArchive(ArchivedTaskList tasks) throws IOException {
        try {
            if (isPathToArchiveExists) {
                overwriteExistingFile(pathToArchive);
            }
            writeToArchive(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void overwriteExistingFile(Path path) throws IOException {
        java.nio.file.Files.write(path, "".getBytes(),
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    private void writeToData(TaskList tasks) throws IOException {
        for (Task t : tasks.tasks) {
            byte[] bytes = t.saveTask().getBytes();
            if (!isPathToDataExists) {
                java.nio.file.Files.write(pathToData, bytes);
                isPathToDataExists = true;
            } else {
                java.nio.file.Files.write(pathToData, bytes, StandardOpenOption.APPEND);
            }
        }
    }

    private void writeToArchive(ArchivedTaskList tasks) throws IOException {
        for (Task t : tasks.archivedTasks) {
            byte[] bytes = t.saveTask().getBytes();
            if (!isPathToArchiveExists) {
                java.nio.file.Files.write(pathToArchive, bytes);
                isPathToArchiveExists = true;
            } else {
                java.nio.file.Files.write(pathToArchive, bytes, StandardOpenOption.APPEND);
            }
        }
    }

    /**
     * Loads data from data.txt into tasks ArrayList if the file exists.
     * Else, it creates a new ArrayList.
     *
     * @return ArrayList of tasks.
     * @throws FileNotFoundException If data.txt file cannot be found.
     */
    public ArrayList<Task> loadData() throws FileNotFoundException {
        if (isPathToDataExists) {
            Scanner taskList = new Scanner(new File(String.valueOf(pathToData)));
            return createTaskArrayList(taskList);
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Loads archive from archive.txt into archivedTasks ArrayList if the file exists.
     * Else, it creates a new ArrayList.
     *
     * @return ArrayList of archived tasks.
     * @throws FileNotFoundException If archive.txt file cannot be found.
     */
    public ArrayList<Task> loadArchive() throws FileNotFoundException {
        if (isPathToArchiveExists) {
            Scanner archivedTaskList = new Scanner(new File(String.valueOf(pathToArchive)));
            return createTaskArrayList(archivedTaskList);
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Creates ArrayList of tasks using the data.txt file.
     *
     * @param taskList TaskList from the data.txt file.
     * @return ArrayList of tasks.
     */
    private ArrayList<Task> createTaskArrayList(Scanner taskList) {
        ArrayList<Task> tasks = new ArrayList<>();

        // Go through the task list from file
        while (taskList.hasNextLine()) {
            String currentTask = taskList.nextLine();
            char typeOfTask = currentTask.charAt(0);
            boolean isTaskDone = (currentTask.charAt(2) == '1');
            int temp = currentTask.indexOf('|');
            Task taskToAdd = new Task("");

            switch(typeOfTask) {
            case('T'):
                taskToAdd = new Todo(currentTask.substring(4));
                break;
            case('D'):
                taskToAdd = new Deadline(currentTask.substring(4, temp - 1),
                        LocalDate.parse(currentTask.substring(temp + 1)));
                break;
            case('E'):
                taskToAdd = new Event(currentTask.substring(4, temp - 1),
                        LocalDateTime.parse(currentTask.substring(temp + 1)));
                break;
            default:
                break;
            }

            // Mark tasks as done if they are already done
            if (isTaskDone) {
                taskToAdd.markAsDone();
            }

            tasks.add(taskToAdd);
        }
        return tasks;
    }
}
