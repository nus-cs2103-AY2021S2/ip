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
        directory.mkdirs();
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
            if (taskString.length() < 9) {
                throw new SaveFileInvalidFormatException();
            }

            Task task;
            char taskType = taskString.charAt(0);
            char taskCompletion = taskString.charAt(4);
            String taskDescription;
            String taskDateTime;

            if (taskCompletion != '1' && taskCompletion != '0') {
                throw new SaveFileInvalidFormatException();
            }

            if (taskType == 'T') {
                taskDescription = taskString.substring(8);
                task = new TodoTask(taskDescription);
            } else if (taskType == 'D') {
                int dateTimeIndex = taskString.substring(8).indexOf('|');
                if (dateTimeIndex == -1) {
                    throw new SaveFileInvalidFormatException();
                }

                taskDescription = taskString.substring(8, dateTimeIndex + 8).trim();
                taskDateTime = taskString.substring(dateTimeIndex + 9).trim();
                if (taskDateTime.isEmpty()) {
                    throw new SaveFileInvalidFormatException();
                }

                int timeIndex = taskDateTime.indexOf('|');
                if (timeIndex == -1) {
                    LocalDate taskDate = LocalDate.parse(taskDateTime);
                    task = new DeadlineTask(taskDescription, taskDate);
                } else {
                    String taskDateString = taskDateTime.substring(0, timeIndex).trim();
                    LocalDate taskDate = LocalDate.parse(taskDateString);
                    String taskTimeString = taskDateTime.substring(timeIndex + 1).trim();
                    LocalTime taskTime = LocalTime
                            .parse(taskTimeString, DateTimeFormatter.ofPattern("HHmm"));
                    task = new DeadlineTask(taskDescription, taskDate, taskTime);
                }
            } else if (taskType == 'E') {
                int dateIndex = taskString.substring(8).indexOf('|');
                if (dateIndex == -1) {
                    throw new SaveFileInvalidFormatException();
                }

                taskDescription = taskString.substring(8, dateIndex + 8).trim();
                taskDateTime = taskString.substring(dateIndex + 9).trim();
                if (taskDateTime.isEmpty()) {
                    throw new SaveFileInvalidFormatException();
                }

                int timeIndex = taskDateTime.indexOf('|');
                if (timeIndex == -1) {
                    LocalDate taskDate = LocalDate.parse(taskDateTime);
                    task = new EventTask(taskDescription, taskDate);
                } else {
                    String taskDateString = taskDateTime.substring(0, timeIndex).trim();
                    LocalDate taskDate = LocalDate.parse(taskDateString);
                    String taskTimeString = taskDateTime.substring(timeIndex + 1).trim();

                    int endTimeIndex = taskTimeString.indexOf('|');
                    if (endTimeIndex == -1) {
                        LocalTime taskStartTime = LocalTime
                                .parse(taskTimeString, DateTimeFormatter.ofPattern("HHmm"));
                        task = new EventTask(taskDescription, taskDate, taskStartTime);
                    } else {
                        String taskStartTimeString = taskTimeString.substring(0, endTimeIndex).trim();
                        LocalTime taskStartTime = LocalTime
                                .parse(taskStartTimeString, DateTimeFormatter.ofPattern("HHmm"));
                        String taskEndTimeString = taskTimeString.substring(endTimeIndex + 1).trim();
                        LocalTime taskEndTime = LocalTime
                                .parse(taskEndTimeString, DateTimeFormatter.ofPattern("HHmm"));
                        task = new EventTask(taskDescription, taskDate, taskStartTime, taskEndTime);
                    }
                }
            } else {
                throw new SaveFileInvalidFormatException();
            }

            if (taskCompletion == '1') {
                task.markAsDone();
            }

            taskList.add(task);
        }
        return taskList;
    }

    /**
     * Saves the contents of TaskList into save file in the valid format.
     *
     * @param tasks TaskList to be saved.
     * @throws IOException If there is error while saving the contents.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        List<Task> taskList = tasks.getTaskList();
        for (Task task : taskList) {
            String saveLine = task.getSaveString() + '\n';
            fileWriter.write(saveLine);
        }
        fileWriter.close();
    }

}
