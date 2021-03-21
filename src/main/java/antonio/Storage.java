package antonio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import antonio.task.DeadlineTask;
import antonio.task.EventTask;
import antonio.task.Task;
import antonio.task.ToDoTask;

/**
 * Encapsulates the methods for handling local storage.
 */
public class Storage {

    private final String path;

    /**
     * Constructs a storage handler object.
     * @param path A string containing the path to the file.
     */
    Storage(String path) {
        this.path = path;
    }

    /**
     * Opens and returns a task list from local storage if present.
     * @return A task list after opening from path.
     * @throws AntonioException If a file is not already present.
     */
    public TaskList open() throws AntonioException {

        File file = new File(path);
        TaskList taskList = new TaskList();
        int taskID = taskList.getSize() + 1;

        if (!file.exists()) {
            createFile(file);
        }

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String newLine = sc.nextLine();
                Task task = formatStringToTask(newLine, taskID);
                taskList.addTask(task);
            }
        } catch (FileNotFoundException e) {
            throw new AntonioException("File not found or is in a wrong format");
        }

        return taskList;
    }

    /**
     * Writes the task list provided to local storage.
     * @param taskList Task list to be written to local storage.
     * @throws IOException If an error occurs during writing to file.
     * @throws AntonioException If unable to build file contents.
     */
    public void write(TaskList taskList) throws IOException, AntonioException {
        assert taskList != null : "taskList has not been initialized";
        FileWriter fw = new FileWriter(path);
        StringBuilder sb = new StringBuilder();
        createStringFormat(taskList, sb);
        fw.write(sb.toString());
        fw.close();
    }

    /**
     * Creates the string to be written as file contents.
     * @param taskList Task list to be written to local storage.
     * @param sb StringBuilder object to hold the formatted strings.
     * @throws AntonioException If unable to format file contents.
     */
    private void createStringFormat(TaskList taskList, StringBuilder sb) throws AntonioException {
        for (int i = 1; i <= taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            sb.append(formatTaskToString(task));
            sb.append("\n");
        }
    }

    /**
     * Creates a file.
     * @param file File object to be created from.
     * @throws AntonioException If unable to create new file.
     */
    private void createFile(File file) throws AntonioException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new AntonioException("Unable to create new file.");
        }
    }

    /**
     * Formats the task into a string to be stored on local storage.
     * @param task Task to be formatted into a string.
     * @return Task that has been formatted into a string.
     * @throws AntonioException If unknown task type.
     */
    private String formatTaskToString(Task task) throws AntonioException {
        assert task != null : "task should not be null";
        String format;
        String description = task.getDescription();
        int status = task.isDone() ? 1 : 0;
        format = serializeTask(task, description, status);
        return format;
    }

    /**
     * Serializes the format of the task depending on its type.
     * @param task Task to be formatted into a string.
     * @param description Description of the task.
     * @param status Status of the task completion.
     * @return Format of the task provided.
     * @throws AntonioException If task type is unknown.
     */
    private String serializeTask(Task task, String description, int status) throws AntonioException {
        String format;
        if (task instanceof ToDoTask) {
            format = String.format("T | %d | %s", status, description);
        } else if (task instanceof DeadlineTask) {
            String deadline = ((DeadlineTask) task).serializeDeadline();
            format = String.format("D | %d | %s | %s", status, description, deadline);
        } else if (task instanceof EventTask) {
            String eventDateTime = ((EventTask) task).serializeEvent();
            format = String.format("E | %d | %s | %s", status, description, eventDateTime);
        } else {
            throw new AntonioException("There is an error in file writing: Unknown task type");
        }
        return format;
    }

    /**
     * Formats a line of string from a file into a Task object.
     * @param newLine New line from string read from file to be converted to a task.
     * @param taskID ID of task to be created.
     * @return Task converted from string read from file.
     * @throws AntonioException If task type is unknown.
     */
    private Task formatStringToTask(String newLine, int taskID) throws AntonioException {
        String[] lineContents = newLine.split(" \\| ");
        String taskType = lineContents[0];
        int status = Integer.parseInt(lineContents[1]);
        String description = lineContents[2];

        switch (taskType) {
        case "T":
            return new ToDoTask(description, taskID, status);
        case "D":
            String time = lineContents[4];
            LocalDate deadline = LocalDate.parse(lineContents[3]);
            return new DeadlineTask(description, taskID, status, deadline, time);
        case "E":
            LocalDate startDate = LocalDate.parse(lineContents[3]);
            String startTime = lineContents[4];
            LocalDate endDate = LocalDate.parse(lineContents[5]);
            String endTime = lineContents[6];
            return new EventTask(description, taskID, status, startDate, startTime, endDate, endTime);
        default:
            throw new AntonioException("There is an error in file reading: Unknown task type");
        }
    }

}
