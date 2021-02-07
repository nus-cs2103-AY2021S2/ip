package popo.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import popo.tasks.DeadlineTask;
import popo.tasks.EventTask;
import popo.tasks.Task;
import popo.tasks.TaskList;
import popo.tasks.ToDoTask;
import popo.utils.OutputDateTimeFormat;

/**
 * Used to read and write to a file.
 */
public class Storage {
    private static final String DEFAULT_FILEPATH = "./data/popo.txt";
    private static final String MESSAGE_INVALID_FILEPATH = "The file path of a storage file should end with '.txt'";
    private static final String MESSAGE_FAILED_INITIALIZATION = "Failed to initialize storage." + "\n"
            + "This error could be due to an invalid file path." + "\n"
            + "Exiting...";
    private static final String MESSAGE_ERROR_WRITING_TO_FILE = "Error writing data to file: ";

    private final Path path;

    /**
     * Creates a {@code Storage} object with the default file path.
     *
     * @throws InvalidStorageFilePathException If the default file path is invalid.
     */
    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_FILEPATH);
    }

    /**
     * Creates a {@code Storage} object with the given file path.
     *
     * @param filePath File path to read or write to.
     * @throws InvalidStorageFilePathException If the file path is invalid.
     */
    public Storage(String filePath) throws InvalidStorageFilePathException {
        if (!isValidFilePath(filePath)) {
            throw new InvalidStorageFilePathException(MESSAGE_INVALID_FILEPATH);
        }
        try {
            path = Path.of(filePath);
        } catch (InvalidPathException ex) {
            throw new InvalidStorageFilePathException(MESSAGE_FAILED_INITIALIZATION);
        }
    }

    /**
     * Checks if a file indicated by the given file path is text file.
     *
     * @param filePath File path to be checked.
     * @return True if the file path ends with '.txt', else false.
     */
    private static boolean isValidFilePath(String filePath) {
        return filePath.endsWith(".txt");
    }

    /**
     * Saves the list of tasks by writing into the file if the list if updated by the previous command.
     *
     * @param taskList Updated task list.
     * @throws StorageException If an error occurs while writing to the file.
     */
    public void saveTasksIfPresent(TaskList taskList) throws StorageException {
        if (taskList == null) {
            return;
        }
        try {
            // Create directories in the file path that do not exist yet
            Path pathToParentDirectory = path.getParent();
            if (pathToParentDirectory != null) {
                Files.createDirectories(pathToParentDirectory);
            }

            List<String> taskStrings = Storage.convertAllTasksToString(taskList);
            assert taskStrings != null;
            Files.write(path, taskStrings);
        } catch (IOException ex) {
            throw new StorageException(MESSAGE_ERROR_WRITING_TO_FILE + path);
        }
    }

    /**
     * Loads the list of tasks found in the file and parses the tasks into an operational format.
     *
     * @return {@code TaskList} that represents the current list of tasks in the file.
     * @throws IOException If an error occurs while reading from the file.
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
     * @param taskString Task string to be converted.
     * @return {@code Task}.
     */
    public static Task convertStringToTask(String taskString) {
        String[] arr = taskString.split("\\s\\|\\s", 4);
        String taskType = arr[0];
        String taskStatus = arr[1];
        String taskName = arr[2];
        boolean isTaskCompleted = taskStatus.equals("1");

        Task task;
        switch (taskType) {
        case ToDoTask.IDENTIFIER:
            task = new ToDoTask(taskName, isTaskCompleted);
            break;
        case DeadlineTask.IDENTIFIER:
            String deadlineTaskDescription = arr[3];
            String[] deadlineTaskDescriptionArr = deadlineTaskDescription.split(",", 2);
            String deadlineDateString = deadlineTaskDescriptionArr[0].strip();
            LocalDate deadlineDate = LocalDate.parse(deadlineDateString, OutputDateTimeFormat.OUTPUT_DATE_FORMAT);
            if (deadlineTaskDescriptionArr.length == 1) {
                // There is no time component
                task = new DeadlineTask(taskName, isTaskCompleted, deadlineDate);
            } else {
                // There is a time component
                assert deadlineTaskDescriptionArr.length == 2 : "There should be a time component string in the array";
                String deadlineTimeString = deadlineTaskDescriptionArr[1].strip();
                LocalTime deadlineTime = LocalTime.parse(deadlineTimeString, OutputDateTimeFormat.OUTPUT_TIME_FORMAT);
                task = new DeadlineTask(taskName, isTaskCompleted, deadlineDate, deadlineTime);
            }
            break;
        case EventTask.IDENTIFIER:
            String eventTaskDescription = arr[3];
            task = new EventTask(taskName, isTaskCompleted, eventTaskDescription);
            break;
        default:
            throw new AssertionError(taskType);
        }
        return task;
    }

    /**
     * Converts a list of tasks into a list of strings formatted to be stored in a file.
     *
     * @param taskList Task list to be converted.
     * @return A list of formatted task strings.
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
     * @param task Task object to be converted.
     * @return Formatted string describing the task.
     */
    public static String convertTaskToString(Task task) {
        StringBuilder encodedTaskString = new StringBuilder();
        encodedTaskString.append(task.getTaskType());
        encodedTaskString.append(" | ");
        encodedTaskString.append(task.isDone() ? "1" : "0");
        encodedTaskString.append(" | ");
        encodedTaskString.append(task.getName());

        String taskType = task.getTaskType();
        switch (taskType) {
        case ToDoTask.IDENTIFIER:
            return encodedTaskString.toString();
        case DeadlineTask.IDENTIFIER:
            DeadlineTask deadlineTask = (DeadlineTask) task;
            encodedTaskString.append(" | ");
            encodedTaskString.append(deadlineTask.getDeadline());
            return encodedTaskString.toString();
        case EventTask.IDENTIFIER:
            EventTask eventTask = (EventTask) task;
            encodedTaskString.append(" | ");
            encodedTaskString.append(eventTask.getEventTime());
            return encodedTaskString.toString();
        default:
            throw new AssertionError(taskType);
        }
    }
}
