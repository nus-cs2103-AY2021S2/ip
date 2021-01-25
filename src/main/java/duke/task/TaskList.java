package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    public static void addTask(Task task, ArrayList<Task> tasks) throws DukeException {
        try {
            tasks.add(task);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static void markDone(Task task) throws DukeException {
        try {
            task.markDone();
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static Task deleteTask(int taskId, ArrayList<Task> tasks) throws DukeException {
        try {
            return tasks.remove(taskId);
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
