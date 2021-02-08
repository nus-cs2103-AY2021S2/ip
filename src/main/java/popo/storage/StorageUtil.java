package popo.storage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

import popo.tasks.DeadlineTask;
import popo.tasks.DurationTask;
import popo.tasks.EventTask;
import popo.tasks.PeriodTask;
import popo.tasks.Task;
import popo.tasks.TaskList;
import popo.tasks.ToDoTask;
import popo.utils.OutputDateTimeFormat;

/**
 * Contains utility methods for Storage class.
 */
public class StorageUtil {
    private static final String MESSAGE_ERROR_READING_FROM_FILE = "Error parsing data from file..." + "\n"
            + "The file may have been corrupted or is in the wrong format.";

    private static final int TASK_NOT_COMPLETED = 0;
    private static final int TASK_COMPLETED = 1;

    private static final String UNIT_DAYS = "day(s)";
    private static final String UNIT_HOURS = "hour(s)";
    private static final String UNIT_MINUTES = "minute(s)";

    /**
     * Checks if a file indicated by the given file path is text file.
     *
     * @param filePath File path to be checked.
     * @return True if the file path ends with '.txt', else false.
     */
    public static boolean isValidFilePath(String filePath) {
        return filePath.endsWith(".txt");
    }

    /**
     * Converts a task string into a {@code Task} object.
     *
     * @param taskString Task string to be converted.
     * @return {@code Task}.
     * @throws StorageException If an error occurs while parsing the data from the file.
     */
    public static Task convertStringToTask(String taskString) throws StorageException {
        String[] arr = taskString.split("\\|", 5);
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
            case DurationTask.IDENTIFIER:
                String durationTaskDescription = arr[3].strip();
                return createDurationTaskFromString(taskName, isTaskCompleted, durationTaskDescription);
            default:
                throw new StorageException(MESSAGE_ERROR_READING_FROM_FILE);
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new StorageException(MESSAGE_ERROR_READING_FROM_FILE);
        }
    }

    /**
     * Converts a list of tasks into a list of strings formatted to be stored in a file.
     *
     * @param taskList Task list to be converted.
     * @return A list of formatted task strings.
     */
    public static List<String> convertAllTasksToString(TaskList taskList) {
        List<String> taskStrings = new ArrayList<>();
        taskList.unmodifiableList()
                .stream()
                .map(task -> convertTaskToString(task))
                .forEach(taskString -> taskStrings.add(taskString));
        return taskStrings;
    }

    /**
     * Converts a {@code Task} into a formatted task string depending on the type of the task.
     *
     * @param task Task object to be converted.
     * @return Formatted string describing the task.
     */
    public static String convertTaskToString(Task task) {
        StringBuilder encodedTaskString = new StringBuilder();
        encodedTaskString.append(task.getTaskType());
        encodedTaskString.append(" | ").append(task.isDone() ? "1" : "0");
        encodedTaskString.append(" | ").append(task.getName());

        String taskType = task.getTaskType();
        switch (taskType) {
        case ToDoTask.IDENTIFIER:
            break;
        case DeadlineTask.IDENTIFIER:
            DeadlineTask deadlineTask = (DeadlineTask) task;
            encodedTaskString.append(" | ").append(deadlineTask.getDeadline());
            break;
        case EventTask.IDENTIFIER:
            EventTask eventTask = (EventTask) task;
            encodedTaskString.append(" | ").append(eventTask.getEventTime());
            break;
        case PeriodTask.IDENTIFIER:
            PeriodTask periodTask = (PeriodTask) task;
            encodedTaskString.append(" | ").append(periodTask.getStartDate());
            encodedTaskString.append(" | ").append(periodTask.getEndDate());
            break;
        case DurationTask.IDENTIFIER:
            DurationTask durationTask = (DurationTask) task;
            encodedTaskString.append(" | ").append(durationTask.getDuration());
            break;
        default:
            throw new AssertionError(taskType);
        }
        return encodedTaskString.toString();
    }

    /**
     * Checks if a task is completed based on the status string.
     * If the status code is 0, the task is to be marked as not completed.
     * If the status code is 1, the task is to be marked as completed.
     *
     * @param status Status string representing if the task is completed.
     * @return True if the status code is 1, false if the status code is 0.
     * @throws StorageException If the status code is not 0 or 1.
     */
    public static boolean isTaskCompleted(String status) throws StorageException {
        try {
            int taskStatus = Integer.parseInt(status);
            switch (taskStatus) {
            case TASK_NOT_COMPLETED:
                return false;
            case TASK_COMPLETED:
                return true;
            default:
                throw new StorageException(MESSAGE_ERROR_READING_FROM_FILE);
            }
        } catch (NumberFormatException ex) {
            throw new StorageException(MESSAGE_ERROR_READING_FROM_FILE);
        }
    }

    /**
     * Creates a {@code ToDoTask} object from the given task name and task completion status.
     *
     * @param name            Name of the task.
     * @param isTaskCompleted Boolean indicating whether the task has been completed.
     * @return {@code ToDoTask}.
     */
    public static Task createToDoTaskFromString(String name, boolean isTaskCompleted) {
        return new ToDoTask(name, isTaskCompleted);
    }

    /**
     * Creates a {@code DeadlineTask} object from the given task name, task completion status, and deadline.
     *
     * @param name            Name of the task.
     * @param isTaskCompleted Boolean indicating whether the task has been completed.
     * @param deadline        Deadline of the task.
     * @return {@code DeadlineTask}.
     * @throws StorageException If an error occurs while parsing the data from the file.
     */
    public static Task createDeadlineTaskFromString(String name, boolean isTaskCompleted, String deadline)
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

    /**
     * Creates an {@code EventTask} object from the given task name, task completion status, and event time.
     *
     * @param name            Name of the task.
     * @param isTaskCompleted Boolean indicating whether the task has been completed.
     * @param eventTime       Event time of the task.
     * @return {@code EventTask}.
     */
    public static Task createEventTaskFromString(String name, boolean isTaskCompleted, String eventTime) {
        return new EventTask(name, isTaskCompleted, eventTime);
    }

    /**
     * Creates a {@code PeriodTask} object from the given task name, task completion status,
     * starting date and ending date.
     *
     * @param name            Name of the task.
     * @param isTaskCompleted Boolean indicating whether the task has been completed.
     * @param startDateString Starting date of the task.
     * @param endDateString   Ending date of the task.
     * @return {@code PeriodTask}.
     * @throws StorageException If an error occurs while parsing the data from the file.
     */
    public static Task createPeriodTaskFromString(String name, boolean isTaskCompleted, String startDateString,
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
     * Creates an {@code DurationTask} object from the given task name, task completion status, and duration.
     *
     * @param name            Name of the task.
     * @param isTaskCompleted Boolean indicating whether the task has been completed.
     * @param durationString  Duration of the task.
     * @return {@code DurationTask}.
     * @throws StorageException If an error occurs while parsing the data from the file.
     */
    public static Task createDurationTaskFromString(String name, boolean isTaskCompleted, String durationString)
            throws StorageException {
        try {
            String[] durationArr = durationString.split("\\s", 2);
            String amountString = durationArr[0].strip();
            String unitString = durationArr[1].strip();
            long amount = Long.parseLong(amountString);
            TemporalUnit unit;
            switch (unitString) {
            case UNIT_DAYS:
                unit = ChronoUnit.DAYS;
                break;
            case UNIT_HOURS:
                unit = ChronoUnit.HOURS;
                break;
            case UNIT_MINUTES:
                unit = ChronoUnit.MINUTES;
                break;
            default:
                throw new StorageException(MESSAGE_ERROR_READING_FROM_FILE);
            }
            assert unit != null;
            Duration duration = Duration.of(amount, unit);
            return new DurationTask(name, isTaskCompleted, duration);
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            throw new StorageException(MESSAGE_ERROR_READING_FROM_FILE);
        }
    }
}
