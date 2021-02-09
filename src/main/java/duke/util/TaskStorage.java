package duke.util;

import duke.exception.DukeStorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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

/**
 * Class representing a storage for the user's tasks.
 */
public class TaskStorage {
    private File file;

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
                char taskType = task.toString().charAt(1);
                assert(taskType == 'T' || taskType == 'D' || taskType == 'E');
                int done = task.isDone() ? 1 : 0;
                String description = task.getDescription();
                String details = "";
                switch (taskType) {
                case 'D':
                    Deadline deadline = (Deadline) task;
                    details = deadline.getDateTime();
                    break;
                case 'E':
                    Event event = (Event) task;
                    details = event.getDateTime();
                    break;
                default:
                }
                writer.write(taskType + " | " + done + " | " +  description +
                        (details.isBlank() ? "" : " | " + details));
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
                assert(taskType.equals("T") || taskType.equals("D") || taskType.equals("D"));
                String isDone = taskInfo[1];
                assert(isDone.equals("1") || isDone.equals("0")) : "Save file has been corrupted!";
                String description = taskInfo[2];
                assert(!description.isBlank()) : "Save file has been corrupted!";
                switch (taskType) {
                case "T":
                    retrievedTasks.add(new ToDo(description, isDone));
                    break;
                case "D":
                    String deadlineDetails = taskInfo[3];
                    assert(!deadlineDetails.isBlank()) : "Save file has been corrupted!";
                    LocalDateTime deadline = LocalDateTime.parse(deadlineDetails, formatter);
                    retrievedTasks.add(new Deadline(description, isDone, deadline));
                    break;
                case "E":
                    String eventDetails = taskInfo[3];
                    assert(!eventDetails.isBlank()) : "Save file has been corrupted!";
                    LocalDateTime eventTime = LocalDateTime.parse(eventDetails, formatter);
                    retrievedTasks.add(new Event(description, isDone, eventTime));
                    break;
                default:
                }
            }
        } catch (FileNotFoundException e){
            throw new DukeStorageException("Save file is missing/corrupted!");
        }
        return new TaskList(retrievedTasks);
    }
}
