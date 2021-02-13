package bob.processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import bob.BobException;
import bob.gui.MainWindow;
import bob.task.Task;
import bob.task.TaskList;

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
    public TaskList load() throws BobException {
        File tasksFile = loadFile();
        return readFile(tasksFile);
    }

    /**
     * Returns a File in the specified directory.
     *
     * @return The File that stores the list of tasks in the hard disk.
     * @throws BobException if the file is unable to be created
     */
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

    /**
     * Load the data in the file into an ArrayList.
     *
     * @param tasksFile  The file to be read.
     * @return An ArrayList with the tasks loaded inside.
     * @throws BobException if no such file is found.
     */
    public TaskList readFile(File tasksFile) throws BobException {
        TaskList listOfTasks = new TaskList();
        Parser parser = new Parser();
        try {
            Scanner fileReader = new Scanner(tasksFile);
            while (fileReader.hasNextLine()) {
                String nextLine = fileReader.nextLine();
                Task nextTask = parser.parseLine(nextLine);
                listOfTasks.getTaskList().add(nextTask);
                LocalDateTime reminderDateTime = parser.parseReminderDateTime(nextLine);
                if (reminderDateTime != null && reminderDateTime.isAfter(LocalDateTime.now())) {
                    listOfTasks.addReminder(reminderDateTime, nextTask);
                    nextTask.addReminder(reminderDateTime);
                }
            }
            rewrite(listOfTasks);
        } catch (FileNotFoundException e) {
            throw new BobException("No suitable file found.", e);
        }
        return listOfTasks;
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
            LocalDateTime reminderDateTime = task.getReminderDateTime();
            LocalDateTime dateTime = task.getDateTime();
            String dateTimeString = "";
            String reminderString = "";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
            if (dateTime != null) {
                dateTimeString = " | " + dateTime.format(dateFormatter);
            }
            if (reminderDateTime != null) {
                reminderString = " R: " + reminderDateTime.format(dateFormatter);
            }
            fw.write(type + " | " + done + " | " + name
                    + dateTimeString + reminderString + System.lineSeparator());
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
