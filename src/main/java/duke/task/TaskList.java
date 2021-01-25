package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class containing methods to edit the program's existing task list.
 */
public class TaskList {

    /**
     * Adds a task to an ArrayList of tasks.
     *
     * @param task  task to be added
     * @param tasks ArrayList to be added to
     * @throws DukeException if ArrayList is not initialised
     */
    public static void addTask(Task task, ArrayList<Task> tasks) throws DukeException {
        try {
            tasks.add(task);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Deletes a task from an ArrayList of tasks.
     *
     * @param taskId index of the task to be deleted
     * @param tasks  ArrayList to delete the task from
     * @return Task that was deleted
     * @throws DukeException if ArrayList is not initialised or taskId is invalid
     */
    public static Task deleteTask(int taskId, ArrayList<Task> tasks) throws DukeException {
        try {
            return tasks.remove(taskId);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Converts string into LocalDateTime object.
     *
     * @param date String representing date
     * @return LocalDateTime object
     * @throws DukeException if format of the date String is invalid
     */
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
