package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;
import duke.exception.DukeTaskException;
import duke.exception.DukeSaveException;
import duke.exception.DukeLoadException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String PATH = "data/duke.txt";

    public static void saveTasks(List<Task> tasks) throws DukeSaveException {
        // Create the 'data' folder if missing
        File dir = new File("data");
        if(!dir.exists()) {
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

    public static void loadTasksTo(TaskManager taskManager) throws DukeLoadException {
        // Create the 'data' folder if missing
        File dir = new File("data");
        if(!dir.exists()) {
            dir.mkdir();
        }

        // Load the save file or create one if missing
        File file = new File(PATH);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");

        try {
            // If the save file already exists, load its tasks
            if(!file.createNewFile()) {
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()) {
                    String[] splits = scanner.nextLine().split(" \\| ");
                    boolean isDone = splits[1].equals("1");

                    switch (splits[0]) {
                    case "T":
                        ToDo toDo = taskManager.addToDo(splits[2]);
                        if(isDone) {
                            toDo.markAsDone();
                        }
                        break;
                    case "D":
                        LocalDateTime dateTime = LocalDateTime.parse(splits[3], formatter);
                        Deadline deadline = taskManager.addDeadline(splits[2], dateTime);
                        if(isDone) {
                            deadline.markAsDone();
                        }
                        break;
                    case "E":
                        LocalDateTime start = LocalDateTime.parse(splits[3], formatter);
                        LocalDateTime end = LocalDateTime.parse(splits[4], formatter);
                        Event event = taskManager.addEvent(splits[2], start, end);
                        if(isDone) {
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
