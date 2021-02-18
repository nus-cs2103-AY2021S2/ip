package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import duke.exception.DukeStorageException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * Class representing a storage for the user's tasks.
 */
public class TaskStorage {
    private final File file;
    private final String TODO = "T";
    private final String DEADLINE = "D";
    private final String EVENT = "E";
    private final String DONE = "1";
    private final String NOT_DONE = "0";

    /**
     * Constructor of TaskStorage.
     *
     * @param path Path of the file to read from or write to.
     */
    public TaskStorage(String path) throws DukeStorageException {
        try {
            file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeStorageException("An error has occurred when creating a save file!");
        }
    }

    /**
     * Writes data of existing tasks to a file.
     *
     * @param tasks Existing user's tasks.
     */
    public void storeData(TaskList tasks) throws DukeStorageException {
        try {
            FileWriter writer = new FileWriter(file);
            ListIterator<Task> iterator = tasks.getIterator();
            Task task;
            while (iterator.hasNext()) {
                task = iterator.next();
                String taskType = task.toString().substring(1, 2);
                assert(taskType.equals(TODO) || taskType.equals(DEADLINE) || taskType.equals(EVENT));
                int done = task.isDone() ? 1 : 0;
                String description = task.getDescription();
                String details = "";
                switch (taskType) {
                case DEADLINE:
                    Deadline deadline = (Deadline) task;
                    details = deadline.getDateTime();
                    break;
                case EVENT:
                    Event event = (Event) task;
                    details = event.getDateTime();
                    break;
                default:
                    break;
                }
                writer.write(taskType + " | " + done
                        + " | " + description + (details.isBlank() ? "" : " | " + details));
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeStorageException("An error has occurred when saving the tasks!");
        }
    }

    /**
     * Retrieves data of tasks from file and construct a list of tasks previously added by the user.
     *
     * @return A list of tasks retrieved from the storage.
     */
    public TaskList retrieveData() throws DukeStorageException {
        List<Task> retrievedTasks = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] taskInfo = line.split(" \\| ");
                String taskType = taskInfo[0];
                assert(taskType.equals(TODO) || taskType.equals(DEADLINE) || taskType.equals(EVENT));
                String isDone = taskInfo[1];
                assert(isDone.equals(DONE) || isDone.equals(NOT_DONE)) : "Save file has been corrupted!";
                String description = taskInfo[2];
                assert(!description.isBlank()) : "Save file has been corrupted!";
                switch (taskType) {
                case TODO:
                    retrievedTasks.add(new ToDo(description, isDone));
                    break;
                case DEADLINE:
                    String deadlineDetails = taskInfo[3];
                    assert(!deadlineDetails.isBlank()) : "Save file has been corrupted!";
                    LocalDateTime deadline = LocalDateTime.parse(deadlineDetails, formatter);
                    retrievedTasks.add(new Deadline(description, isDone, deadline));
                    break;
                case EVENT:
                    String eventDetails = taskInfo[3];
                    assert(!eventDetails.isBlank()) : "Save file has been corrupted!";
                    LocalDateTime eventTime = LocalDateTime.parse(eventDetails, formatter);
                    retrievedTasks.add(new Event(description, isDone, eventTime));
                    break;
                default:
                    break;
                }
            }
            return new TaskList(retrievedTasks);
        } catch (FileNotFoundException e) {
            throw new DukeStorageException("Save file is missing/corrupted!");
        }
    }
}
