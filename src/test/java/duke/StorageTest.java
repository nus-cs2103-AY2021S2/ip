package duke;

import duke.bot.Storage;
import duke.bot.TaskManager;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;
import duke.exception.DukeLoadException;
import duke.exception.DukeSaveException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

/** Tests to be run on the read/write functionality of the chat bot */
public class StorageTest {
    /** Directory path of the save file */
    private static final String PATH = "data/duke.txt";

    /** Tests for saving tasks without any previously existing save file */
    @Test
    public void saveTasks_NoExisting() {
        File file = new File(PATH);
        if (file.exists()) {
            file.delete();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");
        LocalDateTime dateTime = LocalDateTime.now();

        List<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("DESCRIPTION 1"));
        tasks.add(new Deadline("DESCRIPTION 2", dateTime, true));
        tasks.add(new Event("DESCRIPTION 3", dateTime, dateTime));

        try {
            Storage.save(tasks);
        } catch(DukeSaveException e) {
            assumeFalse(true, "Failed to save tasks");
        }

        try {
            Scanner scanner = new Scanner(file);

            String content = "";
            while (scanner.hasNextLine()) {
                content += scanner.nextLine() + "\n";
            }

            String expected = "T | 0 | DESCRIPTION 1\nD | 1 | DESCRIPTION 2 | " + dateTime.format(formatter) +
                    "\nE | 0 | DESCRIPTION 3 | " + dateTime.format(formatter) + " | " + dateTime.format(formatter) +
                    "\n";
            assertEquals(expected, content);
        } catch(FileNotFoundException e) {
            assumeFalse(true, "Save file cannot be found after saving");
        }
    }

    /** Tests for loading tasks from a save file when there is no previously existing save file */
    @Test
    public void loadTasksTo_NoExisting() {
        // Create the 'data' folder if missing
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(PATH);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");
            LocalDateTime dateTime = LocalDateTime.now();

            FileWriter writer = new FileWriter(file);
            writer.write("T | 0 | DESCRIPTION 1\nD | 1 | DESCRIPTION 2 | " + dateTime.format(formatter) + "\n" +
                    "E | 0 | DESCRIPTION 3 | " + dateTime.format(formatter) + " | " + dateTime.format(formatter));
            writer.close();

            TaskManager taskManager = new TaskManager();
            Storage.loadTasksTo(taskManager);

            assertEquals("T | 0 | DESCRIPTION 1", taskManager.getTask(0).toSaveFileString());
            assertEquals("D | 1 | DESCRIPTION 2 | " + dateTime.format(formatter),
                    taskManager.getTask(1).toSaveFileString());
            assertEquals("E | 0 | DESCRIPTION 3 | " + dateTime.format(formatter) + " | " +
                    dateTime.format(formatter), taskManager.getTask(2).toSaveFileString());
        } catch(DukeLoadException e) {
            assertFalse(true, "Failed to load task from save");
        } catch(IOException e) {
            assertFalse(true, "Failed to create a mock save file");
        }
    }
}
