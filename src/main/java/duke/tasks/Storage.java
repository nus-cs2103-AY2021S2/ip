package duke.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;

import duke.Launcher;
import duke.ui.Parser;


/**
 * Handles the loading of tasks when the app starts, as well as the saving of tasks when
 * the app terminates.
 */
public class Storage {
    private final String filePath;

    /**
     * Initializes a storage/loader with a file path.
     *
     * @param fileName Name of text file in directory /data/ from which tasks are loaded,
     *                 and to which tasks are saved.
     */
    public Storage(String fileName) {
        this.filePath = this.getSaveFilePath(fileName);
    }

    /**
     * Loads saved tasks and returns them in a <code>TaskList</code> object. Note that, if a text file
     * is non-existent at the specified path, then no loading is done and an empty <code>TaskList</code>
     * is returned.
     *
     * @return Returns a <code>TaskList</code> object containing the loaded tasks (if any).
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(this.filePath);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String taskDetails = sc.nextLine();
                Task newTask = createTaskFromSavedString(taskDetails);
                tasks.addTask(newTask);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            return tasks;
        }
    }

    /**
     * Saves the tasks in the input <code>TaskList</code> into a text file at the specified path.
     * If such a text file already exists, it will be overwritten.
     * If such a text file does not exist, one will be created.
     *
     * @param tasks A collection of <code>Task</code> objects to save.
     */
    public void saveTasks(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(this.filePath);

            for (Task task : tasks.getListOfTasks()) {
                writer.write(convertTaskToSavableString(task));
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a <code>Task</code> object from a <code>String</code> containing a task's details.
     * The <code>String</code> must follow the very specific format of
     * [ Task Type | Status | Task Description | Time if applicable ],
     * for instance [ D | 1 | CS2103 Quiz | 2021-02-12 02:00 ]
     *
     * @param taskDetails A formatted String, containing a task's details, to convert.
     * @return The corresponding <code>Task</code> object.
     */
    private Task createTaskFromSavedString(String taskDetails) {
        // Number of sections refer to the substrings separated by the character "|"
        int numberOfSections = taskDetails.length() - taskDetails.replace("|", "").length() + 1;
        assert numberOfSections == 4;

        String[] taskDetailsArray = taskDetails.split("\\|", 4);

        String taskType = taskDetailsArray[0].trim();
        assert (taskType.equals("T") || taskType.equals("D") || taskType.equals("E"));

        String done = taskDetailsArray[1].trim();
        assert (done.equals("1") || done.equals("0"));

        String description = taskDetailsArray[2].trim();
        assert description.length() > 0;

        // If the task to create is a deadline or an event, the LocalDateTime obtained must not be null.
        // That means, the datetime string must be in a valid format, i.e. YYYY-MM-DD HH:mm or YYYY-MM-DD.
        String dateTimeString = taskDetailsArray[3].trim();
        LocalDateTime dateTime = Parser.convertToDateTime(dateTimeString);
        assert taskType.equals("T") || dateTime != null;

        Task newTask;
        if (taskType.equals("T")) {
            newTask = new ToDo(description);
        } else if (taskType.equals("D")) {
            newTask = new Deadline(description, dateTime);
        } else {
            newTask = new Event(description, dateTime);
        }

        if (done.equals("1")) {
            newTask.markAsDone();
        }

        return newTask;
    }

    /**
     * Converts a <code>Task</code> object into a <code>String</code> containing the task's details.
     * The output <code>String</code> will have the very specific format of
     * [ Task Type | Status | Task Description | Time if applicable ],
     * for instance [ D | 1 | CS2103 Quiz | 2021-02-12 02:00 ]
     *
     * @param task A <code>Task</code> object to convert to a <code>String</code>.
     * @return The corresponding formatted <code>String</code> containing the input task's details.
     */
    private String convertTaskToSavableString(Task task) {
        assert (task instanceof ToDo || task instanceof Deadline || task instanceof Event);

        String dateTimeString = "";
        String taskType;

        if (task instanceof Deadline) {
            taskType = "D";
            dateTimeString = ((Deadline) task).getByDateTimeString();
        } else if (task instanceof Event) {
            taskType = "E";
            dateTimeString = ((Event) task).getAtDateTimeString();
        } else {
            taskType = "T";
        }

        String done = task.isDone() ? "1" : "0";
        String description = task.getDescription();

        return taskType + " | " + done + " | " + description + " | " + dateTimeString + "\n";
    }

    /**
     * Builds the path to the text file from which to load data and to which to save data.
     *
     * @param fileName Name of text file.
     * @return Path to the save file.
     */
    private String getSaveFilePath(String fileName) {
        String appDirectory = URLDecoder.decode(
                Launcher.class
                        .getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .getPath(),
                StandardCharsets.UTF_8);

        // Obtain the path of the directory containing the save file
        String dataDirectory;
        if (appDirectory.endsWith(".jar")) {
            dataDirectory = new File(appDirectory).getParentFile().getPath() + "/data";
        } else {
            dataDirectory = "data";
        }

        if (!new File(dataDirectory).exists()) {
            boolean directoryCreated = new File(dataDirectory).mkdir();
            assert directoryCreated;
        }

        return dataDirectory + "/" + fileName;
    }
}
