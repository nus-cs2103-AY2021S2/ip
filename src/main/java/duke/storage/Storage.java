package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.SaveFileInvalidFormatException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.TodoTask;

/**
 * Responsible for reading and editing save file contents.
 */
public class Storage {
    private String filePath;
    private final char COMPLETE = '1';
    private final char INCOMPLETE = '0';

    /**
     * Constructs a Storage with the given save file location.
     *
     * @param filePath File path of the save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates the directory and save file if it does not exist.
     *
     * @throws IOException If there is error while creating directory or file.
     */
    public void initialize() throws IOException {
        File file = new File(filePath);
        File directory = new File(file.getParent());
        createDirectory(directory);
        createFile(file);
    }

    private void createDirectory(File directory) {
        directory.mkdirs();
    }

    private void createFile(File file) throws IOException {
        file.createNewFile();
    }

    /**
     * Returns the List of Task saved in the save file.
     *
     * @return List of Task saved in the save file.
     * @throws SaveFileInvalidFormatException If format of save file is invalid.
     * @throws FileNotFoundException If save file is not found.
     * @throws DateTimeParseException If format of date and time is invalid.
     */
    public List<Task> load() throws SaveFileInvalidFormatException,
            FileNotFoundException, DateTimeParseException {
        List<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            String taskString = reader.nextLine();
            checkSaveStringLength(taskString);

            char taskCompletion = taskString.charAt(4);
            checkTaskCompletionFormat(taskCompletion);

            Task task = createTasks(taskString);

            markTaskAsDone(task, taskCompletion);

            taskList.add(task);
        }
        return taskList;
    }

    private void markTaskAsDone(Task task, char taskCompletion) {
        if (taskCompletion == COMPLETE) {
            task.markAsDone();
        }
    }

    private Task createTasks(String taskString) throws SaveFileInvalidFormatException {
        char taskType = taskString.charAt(0);
        if (taskType == 'T') {
            return createTodoTask(taskString);
        } else if (taskType == 'D') {
            return createDeadlineTask(taskString);
        } else if (taskType == 'E') {
            return createEventTask(taskString);
        } else {
            throw new SaveFileInvalidFormatException();
        }
    }

    private void checkSaveStringLength(String taskString) throws SaveFileInvalidFormatException {
        if (taskString.length() < 9) {
            throw new SaveFileInvalidFormatException();
        }
    }

    private void checkTaskCompletionFormat(char taskCompletion) throws SaveFileInvalidFormatException {
        if (isWrongTaskCompletionFormat(taskCompletion)) {
            throw new SaveFileInvalidFormatException();
        }
    }

    private boolean isWrongTaskCompletionFormat(char taskCompletion) {
        return taskCompletion != COMPLETE && taskCompletion != INCOMPLETE;
    }

    private TodoTask createTodoTask(String taskString) {
        String taskDescription = taskString.substring(8);
        return new TodoTask(taskDescription);
    }

    private DeadlineTask createDeadlineTask(String taskString) throws SaveFileInvalidFormatException {
        int dateTimeIndex = taskString.substring(8).indexOf('|');
        if (hasNoDateTimeGiven(dateTimeIndex)) {
            throw new SaveFileInvalidFormatException();
        }

        String taskDescription = taskString.substring(8, dateTimeIndex + 8).trim();
        String taskDateTime = taskString.substring(dateTimeIndex + 9).trim();
        if (hasNoDateGiven(taskDateTime)) {
            throw new SaveFileInvalidFormatException();
        }

        int timeIndex = taskDateTime.indexOf('|');
        if (hasNoTimeGiven(timeIndex)) {
            return createDeadlineTaskWithDate(taskDateTime, taskDescription);
        } else {
            return createDeadlineTaskWithDateTime(taskDateTime, taskDescription, timeIndex);
        }
    }

    private boolean hasNoDateTimeGiven(int dateTimeIndex) {
        return dateTimeIndex == -1;
    }

    private boolean hasNoDateGiven(String taskDateTime) {
        return taskDateTime.isEmpty();
    }

    private boolean hasNoTimeGiven(int timeIndex) {
        return timeIndex == -1;
    }

    private DeadlineTask createDeadlineTaskWithDate(String taskDateTime, String taskDescription) {
        LocalDate taskDate = LocalDate.parse(taskDateTime);
        return new DeadlineTask(taskDescription, taskDate);
    }

    private DeadlineTask createDeadlineTaskWithDateTime(String taskDateTime,
            String taskDescription, int timeIndex) {
        String taskDateString = taskDateTime.substring(0, timeIndex).trim();
        LocalDate taskDate = LocalDate.parse(taskDateString);
        String taskTimeString = taskDateTime.substring(timeIndex + 1).trim();
        LocalTime taskTime = LocalTime
                .parse(taskTimeString, DateTimeFormatter.ofPattern("HHmm"));
        return new DeadlineTask(taskDescription, taskDate, taskTime);
    }

    private EventTask createEventTask(String taskString) throws SaveFileInvalidFormatException {
        int dateTimeIndex = taskString.substring(8).indexOf('|');
        if (hasNoDateTimeGiven(dateTimeIndex)) {
            throw new SaveFileInvalidFormatException();
        }

        String taskDescription = taskString.substring(8, dateTimeIndex + 8).trim();
        String taskDateTime = taskString.substring(dateTimeIndex + 9).trim();
        if (hasNoDateGiven(taskDateTime)) {
            throw new SaveFileInvalidFormatException();
        }

        int timeIndex = taskDateTime.indexOf('|');
        if (hasNoTimeGiven(timeIndex)) {
            return createEventTaskWithDate(taskDateTime, taskDescription);
        } else {
            String taskDateString = taskDateTime.substring(0, timeIndex).trim();
            LocalDate taskDate = LocalDate.parse(taskDateString);
            String taskTimeString = taskDateTime.substring(timeIndex + 1).trim();

            int endTimeIndex = taskTimeString.indexOf('|');
            if (hasNoEndTime(endTimeIndex)) {
                return createEventTaskWithStartTime(taskTimeString, taskDescription, taskDate);
            } else {
                return createEventTaskWithEndTime(taskTimeString, taskDescription, taskDate, endTimeIndex);
            }
        }
    }

    private boolean hasNoEndTime(int endTimeIndex) {
        return endTimeIndex == -1;
    }

    private EventTask createEventTaskWithDate(String taskDateTime, String taskDescription) {
        LocalDate taskDate = LocalDate.parse(taskDateTime);
        return new EventTask(taskDescription, taskDate);
    }

    private EventTask createEventTaskWithStartTime(String taskTimeString,
            String taskDescription, LocalDate taskDate) {
        LocalTime taskStartTime = LocalTime
                .parse(taskTimeString, DateTimeFormatter.ofPattern("HHmm"));
        return new EventTask(taskDescription, taskDate, taskStartTime);
    }

    private EventTask createEventTaskWithEndTime(String taskTimeString,
            String taskDescription, LocalDate taskDate, int endTimeIndex) {
        String taskStartTimeString = taskTimeString.substring(0, endTimeIndex).trim();
        LocalTime taskStartTime = LocalTime
                .parse(taskStartTimeString, DateTimeFormatter.ofPattern("HHmm"));
        String taskEndTimeString = taskTimeString.substring(endTimeIndex + 1).trim();
        LocalTime taskEndTime = LocalTime
                .parse(taskEndTimeString, DateTimeFormatter.ofPattern("HHmm"));
        return new EventTask(taskDescription, taskDate, taskStartTime, taskEndTime);
    }

    /**
     * Saves the contents of TaskList into save file in the valid format.
     *
     * @param tasks TaskList to be saved.
     * @throws IOException If there is error while saving the contents.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        saveContents(fileWriter, tasks);
        fileWriter.close();
    }

    private void saveContents(FileWriter fileWriter, TaskList tasks) throws IOException {
        List<Task> taskList = tasks.getTaskList();
        for (Task task : taskList) {
            String saveLine = task.getSaveString() + '\n';
            fileWriter.write(saveLine);
        }
    }

}
