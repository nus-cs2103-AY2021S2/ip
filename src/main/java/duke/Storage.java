package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;

/**
 * Encapsulates the methods for handling local storage.
 */
public class Storage {

    private final String path;

    /**
     * Constructor for storage.
     * @param path A string containing the path to the file.
     */
    Storage(String path) {
        this.path = path;
    }

    /**
     * Opens and returns a task list from local storage if present.
     * @return A task list after opening from path.
     * @throws DukeException If a file is not already present.
     */
    public TaskList open() throws DukeException {

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
                Task task = processFileContents(newLine, taskID);
                taskList.addTask(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found or is in a wrong format");
        }

        return taskList;
    }

    /**
     * Writes the task list provided to local storage.
     * @param taskList Task list to be written to local storage.
     * @throws IOException If an error occurs during writing to file.
     * @throws DukeException If unable to format file contents.
     */
    public void write(TaskList taskList) throws IOException, DukeException {
        assert taskList != null : "taskList has not been initialized";
        FileWriter fw = new FileWriter(path);
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            sb.append(formatFileContents(task));
            sb.append("\n");
        }
        fw.write(sb.toString());
        fw.close();
    }

    /**
     * Creates a file.
     * @param file File object to be created from.
     * @throws DukeException If unable to create new file.
     */
    private void createFile(File file) throws DukeException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Unable to create new file.");
        }
    }

    /**
     * Format task into a string to be stored on local storage.
     * @param task Task to be formatted into a string.
     * @return Task that has been formatted into a string.
     * @throws DukeException If unknown task type.
     */
    private String formatFileContents(Task task) throws DukeException {
        assert task != null : "task should not be null";
        String format;
        String description = task.getDescription();
        int status = task.isDone() ? 1 : 0;

        if (task instanceof ToDoTask) {
            format = String.format("T | %d | %s", status, description);
        } else if (task instanceof DeadlineTask) {
            String deadline = ((DeadlineTask) task).serializeDeadline();
            format = String.format("D | %d | %s | %s", status, description, deadline);
        } else if (task instanceof EventTask) {
            String eventDateTime = ((EventTask) task).serializeEvent();
            format = String.format("E | %d | %s | %s", status, description, eventDateTime);
        } else {
            throw new DukeException("Error in file writing: Unknown task type");
        }
        return format;
    }

    /**
     * Formats a line of string from a file into a Task object.
     * @param newLine Line from string read from file to be converted to a task.
     * @param taskID ID of task to be created.
     * @return Task converted from string read from file.
     * @throws DukeException If task type is unknown.
     */
    private Task processFileContents(String newLine, int taskID) throws DukeException {
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
            LocalDate eventDate = LocalDate.parse(lineContents[3]);
            String startTime = lineContents[4];
            String endTime = lineContents[5];
            return new EventTask(description, taskID, status, eventDate, startTime, endTime);
        default:
            throw new DukeException("Error in file reading: Unknown task type");
        }
    }

}
