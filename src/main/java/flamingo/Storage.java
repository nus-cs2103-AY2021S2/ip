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
    protected boolean pathExists;
    private String currentDirectory;
    private Path path;

    /**
     * Creates a new Storage.
     */
    public Storage() {
        this.currentDirectory = System.getProperty("user.dir");
        this.path = java.nio.file.Paths.get(currentDirectory, "data.txt");
        this.pathExists = java.nio.file.Files.exists(path);
    }

    public boolean getPathExists() {
        return pathExists;
    }

    /**
     * Transfers contents from tasks ArrayList to data.txt file.
     * If data.txt file exists, the previous content is cleared.
     *
     * @param tasks TaskList of tasks.
     * @throws IOException If data cannot be saved into file.
     */
    public void saveData(TaskList tasks) throws IOException {
        try {
            if (pathExists) {
                java.nio.file.Files.write(path, "".getBytes(),
                        StandardOpenOption.TRUNCATE_EXISTING);
            }

            for (Task t : tasks.tasks) {
                byte[] bytes = t.saveTask().getBytes();
                if (!pathExists) {
                    java.nio.file.Files.write(path, bytes);
                    pathExists = true;
                } else {
                    java.nio.file.Files.write(path, bytes, StandardOpenOption.APPEND);
                }
            }
        } catch (IOException e) {
            System.out.println("\nOh no Flamingo! I cannot save the data!\n");
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
        if (pathExists) {
            Scanner taskList = new Scanner(new File(String.valueOf(path)));
            ArrayList<Task> tasks = new ArrayList<>();
            while (taskList.hasNextLine()) {
                String currentTask = taskList.nextLine();
                char typeOfTask = currentTask.charAt(0);
                boolean isTaskDone = (currentTask.charAt(2) == '1');
                int temp = currentTask.indexOf('|');
                Task taskToAdd = new Task("");

                if (typeOfTask == 'T') {
                    taskToAdd = new Todo(currentTask.substring(4));
                } else if (typeOfTask == 'D') {
                    taskToAdd = new Deadline(currentTask.substring(4, temp - 1),
                            LocalDate.parse(currentTask.substring(temp + 1)));
                } else if (typeOfTask == 'E') {
                    taskToAdd = new Event(currentTask.substring(4, temp - 1),
                            LocalDateTime.parse(currentTask.substring(temp + 1)));
                }
                if (isTaskDone) {
                    taskToAdd.markAsDone();
                }
                tasks.add(taskToAdd);
            }
            return tasks;
        } else {
            return new ArrayList<>();
        }
    }
}
