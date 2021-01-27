package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
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
    private Ui ui;

    /**
     * Constructor for TaskStorage.
     *
     * @param path Path of the file to read from or write to.
     */
    public TaskStorage(String path) {
        try {
            ui = new Ui();
            file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (Exception e) {
            ui.print(e.getMessage());
        }
    }

    /**
     * Writes data of existing tasks to a file.
     *
     * @param tasks Existing user's tasks.
     */
    public void storeData(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(file);
            ListIterator<Task> iter = tasks.listIterator();
            Task task;
            while (iter.hasNext()) {
                task = iter.next();
                char taskType = task.toString().charAt(1);
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
        } catch (Exception e) {
            ui.print(e.getMessage());
        }
    }

    /**
     * Retrieve data of tasks from file and construct a list of tasks previously added by the user.
     *
     * @return A list of tasks retrieved from the storage.
     */
    public TaskList retrieveData() {
        List<Task> retrievedTasks = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] taskInfo = line.split(" \\| ");
                String taskType = taskInfo[0];
                String isDone = taskInfo[1];
                String description = taskInfo[2];
                switch (taskType) {
                    case "T":
                        retrievedTasks.add(new ToDo(description, isDone));
                        break;
                    case "D":
                        String deadlineDetails = taskInfo[3];
                        LocalDateTime deadline = LocalDateTime.parse(deadlineDetails, formatter);
                        retrievedTasks.add(new Deadline(description, isDone, deadline));
                        break;
                    case "E":
                        String eventDetails = taskInfo[3];
                        LocalDateTime eventTime = LocalDateTime.parse(eventDetails, formatter);
                        retrievedTasks.add(new Event(description, isDone, eventTime));
                        break;
                    default:
                }
            }
        } catch (Exception e){
            ui.print(e.getMessage());
        }
        return new TaskList(retrievedTasks);
    }
}
