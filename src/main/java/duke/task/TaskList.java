package duke.task;

import duke.DukeException;
import duke.data.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.Ui.*;
import static duke.data.Data.updateDataFile;

public class TaskList {

    public static void addTask(Task task) throws DukeException {
        try {
            Data.tasks.add(task);
            updateDataFile();
            displayAddedTask(task);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static void markDone(Task task) throws DukeException {
        try {
            task.markDone();
            updateDataFile();
            displayDone(task);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static void deleteTask(String[] command) throws DukeException {
        try {
            Task task = Data.tasks.remove(Integer.parseInt(command[1]) - 1);
            updateDataFile();
            displayRemovedTask(task);
        } catch (Exception e) {
            throw new DukeException("That doesn't seem like a valid order number...");
        }
    }

    public static LocalDateTime convertStringToDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (Exception e) {
            throw new DukeException("There was something wrong with the format of your date and/or time.\n" +
                    "Make sure it's in the format <dd/MM/yyyy HHmm>!");
        }
    }
}
