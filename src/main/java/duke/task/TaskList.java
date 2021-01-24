package duke.task;

import duke.DukeException;
import duke.Duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.Ui.*;

public class TaskList {

    public static void addTask(Task task) {
        Duke.tasks.add(task);
        displayAddedTask(task);
    }

    public static void markDone(String[] command) throws DukeException {
        try {
            Task toMarkDone = Duke.tasks.get(Integer.parseInt(command[1]) - 1);
            toMarkDone.markDone();
            displayDone(toMarkDone);
        } catch (Exception e) {
            throw new DukeException("That doesn't seem like a valid order number...");
        }
    }

    public static void deleteTask(String[] command) throws DukeException {
        try {
            Task task = Duke.tasks.remove(Integer.parseInt(command[1]) - 1);
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
