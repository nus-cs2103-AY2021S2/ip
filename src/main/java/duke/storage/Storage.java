package duke.storage;

import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDoTask;
import duke.utils.OutputDateTimeFormat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to read and write to a file.
 */
public class Storage {
    private static final String DEFAULT_FILEPATH = "./data/duke.txt";

    private final Path path;

    /**
     * Creates a {@code Storage} object with the default file path.
     *
     * @throws InvalidStorageFilePathException if the default file path is invalid
     */
    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_FILEPATH);
    }

    /**
     * Creates a {@code Storage} object with the given file path.
     *
     * @param filePath file path to read or write to
     * @throws InvalidStorageFilePathException if the file path is invalid
     */
    public Storage(String filePath) throws InvalidStorageFilePathException {
        if (!isValidFilePath(filePath)) {
            throw new InvalidStorageFilePathException("The file path of a storage file should end with '.txt'");
        }
        try {
            path = Path.of(filePath);
        } catch (InvalidPathException ex) {
            throw new InvalidStorageFilePathException("Failed to initialize storage. This error could be due to"
                    + "an invalid file path.\nExiting...");
        }
    }

    /**
     * Checks if a file indicated by the given file path is text file.
     *
     * @param filePath file path to be checked
     * @return true if the file path ends with '.txt', else false
     */
    private static boolean isValidFilePath(String filePath) {
        return filePath.endsWith(".txt");
    }

    /**
     * Saves the list of tasks by writing into the file if the list if updated by the previous command.
     *
     * @param taskList updated task list
     * @throws StorageException if an error occurs while writing to the file
     */
    public void saveTasksIfPresent(TaskList taskList) throws StorageException {
        if (taskList == null) {
            return;
        }
        try {
            // Create directories in the file path that do not exist yet
            Path pathToParentDirectory = path.getParent();
            Files.createDirectories(pathToParentDirectory);

            List<String> taskStrings = Storage.convertAllTasksToString(taskList);
            Files.write(path, taskStrings);
        } catch (IOException ex) {
            throw new StorageException("Error writing data to file: " + path);
        }
    }

    /**
     * Loads the list of tasks found in the file and parses the tasks into an operational format.
     *
     * @return {@code TaskList} that represents the current list of tasks in the file
     * @throws IOException if an error occurs while reading from the file
     */
    public TaskList loadTasks() throws IOException {
        TaskList taskList = new TaskList();
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return taskList;
        }
        List<String> taskStrings = Files.readAllLines(path);
        for (String s : taskStrings) {
            taskList.addTask(Storage.convertStringToTask(s));
        }
        return taskList;
    }

    /**
     * Converts a task string into a {@code Task} object.
     *
     * @param taskString task string to be converted
     * @return {@code Task}
     */
    public static Task convertStringToTask(String taskString) {
        Task task = null;

        String[] arr = taskString.split("\\s\\|\\s");
        String taskType = arr[0];
        String taskStatus = arr[1];
        String taskName = arr[2];
        boolean isTaskCompleted = taskStatus.equals("1");

        switch (taskType) {
        case ToDoTask.IDENTIFIER:
            task = new ToDoTask(taskName, isTaskCompleted);
            break;
        case DeadlineTask.IDENTIFIER:
            String deadlineTaskDescription = arr[3];
            String[] deadlineTaskDescriptionArr = deadlineTaskDescription.split(",");
            String deadlineDateString = deadlineTaskDescriptionArr[0].strip();
            LocalDate deadlineDate = LocalDate.parse(deadlineDateString, OutputDateTimeFormat.OUTPUT_DATE_FORMAT);
            if (deadlineTaskDescriptionArr.length == 1) {
                // There is no time component
                task = new DeadlineTask(taskName, isTaskCompleted, deadlineDate);
            } else {
                // There is a time component
                String deadlineTimeString = deadlineTaskDescriptionArr[1].strip();
                LocalTime deadlineTime = LocalTime.parse(deadlineTimeString, OutputDateTimeFormat.OUTPUT_TIME_FORMAT);
                task = new DeadlineTask(taskName, isTaskCompleted, deadlineDate, deadlineTime);
            }
            break;
        case EventTask.IDENTIFIER:
            String eventTaskDescription = arr[3];
            task = new EventTask(taskName, isTaskCompleted, eventTaskDescription);
            break;
        }
        return task;
    }

    /**
     * Converts a list of tasks into a list of strings formatted to be stored in a file.
     *
     * @param taskList task list to be converted
     * @return a list of formatted task strings
     */
    public static List<String> convertAllTasksToString(TaskList taskList) {
        List<String> taskStrings = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            taskStrings.add(Storage.convertTaskToString(task));
        }
        return taskStrings;
    }

    /**
     * Converts a {@code Task} into a formatted task string.
     * String format: "taskType | taskStatus | taskName", with an additional " | additionalInfo]"
     * depending on the type of the task.
     *
     * @param task task object to be converted
     * @return formatted string describing the task
     */
    public static String convertTaskToString(Task task) {
        // 
        StringBuilder encodedTaskString = new StringBuilder();

        encodedTaskString.append(task.getTaskType());
        encodedTaskString.append(" | ");

        encodedTaskString.append(task.isDone() ? "1" : "0");
        encodedTaskString.append(" | ");

        encodedTaskString.append(task.getName());

        if (task.getTaskType().equals("D")) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            encodedTaskString.append(" | ");
            encodedTaskString.append(deadlineTask.getDeadline());
        } else if (task.getTaskType().equals("E")) {
            EventTask eventTask = (EventTask) task;
            encodedTaskString.append(" | ");
            encodedTaskString.append(eventTask.getEventTime());
        }
        return encodedTaskString.toString();
    }
}
