package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeLoadException;
import duke.exception.DukeSaveException;
import duke.exception.DukeTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/** An utility class that provide read/write related operations */
public class Storage {
    /** Directory path of the save file */
    private static final String PATH = "data/duke.txt";

    /**
     * Saves all tasks in the current session into the hard disk
     *
     * @param tasks A list of tasks
     * @throws DukeSaveException if there is an issue writing into the hard disk
     */
    public static void saveTasks(List<Task> tasks) throws DukeSaveException {
        // Create the 'data' folder if missing
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(PATH);
        try {
            // Erase any existing list in the file
            new PrintWriter(PATH).close();

            // Save each task as a row in the file
            FileWriter writer = new FileWriter(PATH);
            for (Task task : tasks) {
                writer.write(task.toSaveInfoString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeSaveException("Issue with IO while saving tasks");
        }
    }

    /**
     * Loads tasks from the hard disk into current session's task list
     *
     * @param taskManager TaskManager that contains the current session's task list
     * @throws DukeLoadException if there is an issue reading tasks from the hard disk
     */
    public static void loadTasksTo(TaskManager taskManager) throws DukeLoadException {
        // Create the 'data' folder if missing
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        // Load the save file or create one if missing
        File file = new File(PATH);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");

        try {
            // If the save file already exists, load its tasks
            if (!file.createNewFile()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String[] splits = scanner.nextLine().split(" \\| ");
                    boolean isDone = splits[1].equals("1");

                    switch (splits[0]) {
                    case "T":
                        ToDo toDo = taskManager.addToDo(splits[2]);
                        if (isDone) {
                            toDo.markAsDone();
                        }
                        break;
                    case "D":
                        LocalDateTime dateTime = LocalDateTime.parse(splits[3], formatter);
                        Deadline deadline = taskManager.addDeadline(splits[2], dateTime);
                        if (isDone) {
                            deadline.markAsDone();
                        }
                        break;
                    case "E":
                        LocalDateTime start = LocalDateTime.parse(splits[3], formatter);
                        LocalDateTime end = LocalDateTime.parse(splits[4], formatter);
                        Event event = taskManager.addEvent(splits[2], start, end);
                        if (isDone) {
                            event.markAsDone();
                        }
                        break;
                    default:
                        throw new DukeLoadException("Invalid task type found: " + splits[0]);
                    }
                }
            }
        } catch (IOException e) {
            throw new DukeLoadException("Issue with IO while loading tasks");
        } catch (DukeTaskException e) {
            throw new DukeLoadException(e.getMessage());
        }
    }
}
