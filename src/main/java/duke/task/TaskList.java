package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static duke.Ui.*;

public class TaskList {

    public static void addTask(Task task, ArrayList<Task> tasks) throws DukeException {
        try {
            tasks.add(task);
            displayAddedTask(task);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static void markDone(Task task) throws DukeException {
        try {
            task.markDone();
            displayDone(task);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static void deleteTask(int taskId, ArrayList<Task> tasks) throws DukeException {
        try {
            Task task = tasks.remove(taskId);
            displayRemovedTask(task);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
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
