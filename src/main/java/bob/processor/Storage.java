package bob.processor;

import bob.BobException;
import bob.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores the list of tasks, and is responsible for
 * loading and changing the list of tasks in the hard disk
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the list of tasks from the hard disk into Bob
     *
     * @return An ArrayList containing all the tasks saved in hard disk
     * @throws BobException if the file cannot be found in the hard disk
     */
    public ArrayList<Task> load() throws BobException {
        File tasksFile = loadFile();
        return readFile(tasksFile);
    }

    public File loadFile() throws BobException {
        String currDirectory = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(currDirectory, "data");

        File tasksFile = new File(filePath);
        try {
            if (!java.nio.file.Files.exists(path)) {
                java.nio.file.Files.createDirectory(path);
            }
            if (!tasksFile.exists()) {
                tasksFile.createNewFile();
            }
            return tasksFile;
        } catch (IOException e) {
            throw new BobException("Unable to create file.", e);
        }
    }

    public ArrayList<Task> readFile(File tasksFile) throws BobException {
        ArrayList<Task> taskList = new ArrayList<>();
        Parser parser = new Parser();
        try {
            Scanner fileReader = new Scanner(tasksFile);
            while (fileReader.hasNextLine()) {
                String nextTask = fileReader.nextLine();
                Task newTask = parser.parseLine(nextTask);
                taskList.add(newTask);
            }
        } catch (FileNotFoundException e) {
            throw new BobException("No suitable file found.", e);
        }
        return taskList;
    }

    /**
     * Save the new Task in the hard disk
     *
     * @param task new Task to be saved
     * @throws BobException if the named file exists but is a directory rather
     * than a regular file, does not exist but cannot be created, or cannot
     * be opened for any other reason
     */
    public void appendTask(Task task) throws BobException {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt", true);
            String done = task.getDone() ? "1" : "0";
            String type = task.getType();
            String name = task.getName();
            LocalDateTime dateTime = task.getDateTime();
            String dateTimeString = "";
            if (dateTime != null) {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
                dateTimeString = " | " + dateTime.format(dateFormatter);
            }
            fw.write(type + " | " + done + " | " + name
                    + dateTimeString + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new BobException("File cannot be found.", e);
        }
    }

    /**
     * Update changes in the hard disk
     *
     * @param task The TaskList to be updated
     * @throws BobException if the named file exists but is a directory rather
     * than a regular file, does not exist but cannot be created, or cannot
     * be opened for any other reason
     */
    public void rewrite(TaskList task) throws BobException {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt");
            for (Task nextTask : task.getTaskList()) {
                appendTask(nextTask);
            }
            fw.close();
        } catch (IOException e) {
            throw new BobException("File cannot be opened.", e);
        }
    }
}
