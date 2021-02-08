package popo.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import popo.tasks.DeadlineTask;
import popo.tasks.EventTask;
import popo.tasks.PeriodTask;
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
    private static final String MESSAGE_ERROR_READING_FROM_FILE = "Error parsing data from file..." + "\n"
            + "The file may have been corrupted or is in the wrong format.";
    private static final String MESSAGE_ERROR_WRITING_TO_FILE = "Error writing data to file...";

    private static final int TASK_NOT_COMPLETED = 0;
    private static final int TASK_COMPLETED = 1;

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
    private boolean isValidFilePath(String filePath) {
        return filePath.endsWith(".txt");
    }

    /**
     * Loads the list of tasks found in the file and parses the tasks into an operational format.
     *
     * @return {@code TaskList} that represents the current list of tasks in the file.
     * @throws IOException      If an error occurs while reading from the file.
     * @throws StorageException If an error occurs while parsing the data from the file.
     */
    public TaskList loadTasks() throws IOException, StorageException {
        TaskList taskList = new TaskList();
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return taskList;
        }
        List<String> taskStrings = Files.readAllLines(path);
        for (String taskString : taskStrings) {
            Task task = convertStringToTask(taskString);
            taskList.addTask(task);
        }
        return taskList;
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

            List<String> taskStrings = convertAllTasksToString(taskList);
            assert taskStrings != null;
            Files.write(path, taskStrings);
        } catch (IOException ex) {
            throw new StorageException(MESSAGE_ERROR_WRITING_TO_FILE + path);
        }
    }

    /**
     * Converts a task string into a {@code Task} object.
     *
     * @param taskString Task string to be converted.
     * @return {@code Task}.
     * @throws StorageException If an error occurs while parsing the data from the file.
     */
    private Task convertStringToTask(String taskString) throws StorageException {
        String[] arr = taskString.split("\\|", 5);
        if (arr.length < 3) {
            throw new StorageException(MESSAGE_ERROR_READING_FROM_FILE);
        }
        String taskType = arr[0].strip();
        String taskStatus = arr[1].strip();
        String taskName = arr[2].strip();
        boolean isTaskCompleted = isTaskCompleted(taskStatus);

        try {
            switch (taskType) {
            case ToDoTask.IDENTIFIER:
                return createToDoTaskFromString(taskName, isTaskCompleted);
            case DeadlineTask.IDENTIFIER:
                String deadlineTaskDescription = arr[3].strip();
                return createDeadlineTaskFromString(taskName, isTaskCompleted, deadlineTaskDescription);
            case EventTask.IDENTIFIER:
                String eventTaskDescription = arr[3].strip();
                return createEventTaskFromString(taskName, isTaskCompleted, eventTaskDescription);
            case PeriodTask.IDENTIFIER:
                String periodTaskStartDateString = arr[3].strip();
                String periodTaskEndDateString = arr[4].strip();
                return createPeriodTaskFromString(taskName, isTaskCompleted,
                        periodTaskStartDateString, periodTaskEndDateString);
            default:
                throw new StorageException(MESSAGE_ERROR_READING_FROM_FILE);
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new StorageException(MESSAGE_ERROR_READING_FROM_FILE);
        }
    }

    private boolean isTaskCompleted(String status) throws StorageException {
        int taskStatus = Integer.parseInt(status);
        switch (taskStatus) {
        case TASK_NOT_COMPLETED:
            return false;
        case TASK_COMPLETED:
            return true;
        default:
            throw new StorageException(MESSAGE_ERROR_READING_FROM_FILE);
        }
    }

    private Task createToDoTaskFromString(String name, boolean isTaskCompleted) {
        return new ToDoTask(name, isTaskCompleted);
    }

    private Task createDeadlineTaskFromString(String name, boolean isTaskCompleted, String deadline)
            throws StorageException {
        try {
            String[] deadlineArr = deadline.split(",", 2);
            String deadlineDateString = deadlineArr[0].strip();
            LocalDate deadlineDate = LocalDate.parse(deadlineDateString, OutputDateTimeFormat.OUTPUT_DATE_FORMAT);
            if (deadlineArr.length == 1) {
                // There is no time component
                return new DeadlineTask(name, isTaskCompleted, deadlineDate);
            } else {
                // There is a time component
                assert deadlineArr.length == 2;
                String deadlineTimeString = deadlineArr[1].strip();
                LocalTime deadlineTime = LocalTime.parse(deadlineTimeString, OutputDateTimeFormat.OUTPUT_TIME_FORMAT);
                return new DeadlineTask(name, isTaskCompleted, deadlineDate, deadlineTime);
            }
        } catch (DateTimeParseException ex) {
            throw new StorageException(MESSAGE_ERROR_READING_FROM_FILE);
        }
    }

    private Task createEventTaskFromString(String name, boolean isTaskCompleted, String eventTime) {
        return new EventTask(name, isTaskCompleted, eventTime);
    }

    private Task createPeriodTaskFromString(String name, boolean isTaskCompleted, String startDateString,
                                            String endDateString) throws StorageException {
        try {
            LocalDate startDate = LocalDate.parse(startDateString, OutputDateTimeFormat.OUTPUT_DATE_FORMAT);
            LocalDate endDate = LocalDate.parse(endDateString, OutputDateTimeFormat.OUTPUT_DATE_FORMAT);
            return new PeriodTask(name, isTaskCompleted, startDate, endDate);
        } catch (DateTimeParseException ex) {
            throw new StorageException(MESSAGE_ERROR_READING_FROM_FILE);
        }
    }

    /**
     * Converts a list of tasks into a list of strings formatted to be stored in a file.
     *
     * @param taskList Task list to be converted.
     * @return A list of formatted task strings.
     */
    private List<String> convertAllTasksToString(TaskList taskList) {
        List<String> taskStrings = new ArrayList<>();
        taskList.unmodifiableList()
                .stream()
                .map(task -> convertTaskToString(task))
                .forEach(taskString -> taskStrings.add(taskString));
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
    private String convertTaskToString(Task task) {
        StringBuilder encodedTaskString = new StringBuilder();
        encodedTaskString.append(task.getTaskType());
        encodedTaskString.append(" | ");
        encodedTaskString.append(task.isDone() ? "1" : "0");
        encodedTaskString.append(" | ");
        encodedTaskString.append(task.getName());

        String taskType = task.getTaskType();
        switch (taskType) {
        case ToDoTask.IDENTIFIER:
            break;
        case DeadlineTask.IDENTIFIER:
            DeadlineTask deadlineTask = (DeadlineTask) task;
            encodedTaskString.append(" | ");
            encodedTaskString.append(deadlineTask.getDeadline());
            break;
        case EventTask.IDENTIFIER:
            EventTask eventTask = (EventTask) task;
            encodedTaskString.append(" | ");
            encodedTaskString.append(eventTask.getEventTime());
            break;
        case PeriodTask.IDENTIFIER:
            PeriodTask periodTask = (PeriodTask) task;
            encodedTaskString.append(" | ");
            encodedTaskString.append(periodTask.getStartDate());
            encodedTaskString.append(" | ");
            encodedTaskString.append(periodTask.getEndDate());
            break;
        default:
            throw new AssertionError(taskType);
        }
        return encodedTaskString.toString();
    }
}
