package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents the component of the Duke program
 * that makes sense of user input.
 */
public class Parser {

    protected static final DateTimeFormatter INPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Returns a valid Duke Command after parsing user input.
     *
     * @param input user input.
     * @return a valid Duke Command
     * @throws DukeException when user input
     *                       does not match any valid Duke Commands.
     */
    public Command parseCommand(String input) throws DukeException {
        String[] arr = input.split(" ");
        try {
            return Command.valueOf(arr[0].toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new DukeException("OOPS!!! I'm sorry, but that is an invalid command :-(");
        }
    }

    /**
     * Returns a ToDo based on user input.
     *
     * @param input user input.
     * @return a ToDo based on user input.
     * @throws DukeException when user input does not contain
     *                       the description of the ToDo after the command.
     */
    public ToDo parseToDo(String input) throws DukeException {
        String[] arr = input.split(" ");
        if (arr.length < 2) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new ToDo(input.substring(5));
    }

    /**
     * Returns a Deadline based on user input.
     *
     * @param input user input.
     * @return a Deadline based on user input.
     * @throws DukeException when user input does not contain
     *                       the description of the Deadline after the command,
     *                       the date and time of the Deadline after '/by'
     *                       or when the date and time is not in the correct format.
     */
    public Deadline parseDeadline(String input) throws DukeException {
        String[] arr = input.split(" ");
        String[] arr2 = input.split(" /by ");
        if (arr.length < 2 || arr2[0].split(" ").length < 2) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        if (arr2.length < 2) {
            throw new DukeException("OOPS!!! You did not set a deadline using '/by'.");
        }
        LocalDateTime deadlineDate;
        try {
            deadlineDate = LocalDateTime.parse(arr2[1], INPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("OOPS!!! Please input date and time in the following format: dd-MM-yyyy HHmm.");
        }
        return new Deadline(arr2[0].substring(9), deadlineDate);
    }

    /**
     * Returns an Event based on user input.
     *
     * @param input user input.
     * @return a Event based on user input.
     * @throws DukeException when user input does not contain
     *                       the description of the Event after the command,
     *                       the date and time of the Event after '/at'
     *                       or when the date and time is not in the correct format.
     */
    public Event parseEvent(String input) throws DukeException {
        String[] arr = input.split(" ");
        String[] arr3 = input.split(" /at ");
        if (arr.length < 2 || arr3[0].split(" ").length < 2) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        if (arr3.length < 2) {
            throw new DukeException("OOPS!!! You did not set the date/time of the event using '/at'.");
        }
        LocalDateTime eventDate;
        try {
            eventDate = LocalDateTime.parse(arr3[1], INPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("OOPS!!! Please input date and time in the following format: dd-MM-yyyy HHmm.");
        }
        return new Event(arr3[0].substring(6), eventDate);
    }

    /**
     * Returns a valid int index of the Task
     * to be marked as done in the TaskList.
     *
     * @param input    user input.
     * @param taskList list of Tasks that contains the Task to be marked as done.
     * @return the index of the Task.
     * @throws DukeException when user input does not contain an int value after the command
     *                       or when the index provided does not correspond to any Task in the TaskList.
     */
    public int parseDone(String input, TaskList taskList) throws DukeException {
        ArrayList<Task> tasks = taskList.getTasks();
        String[] arr = input.split(" ");
        if (arr.length < 2) {
            throw new DukeException("OOPS!!! You did not enter the number corresponding to the task.");
        }
        try {
            int argument = Integer.parseInt(arr[1]);
            if (argument < 1 || argument > tasks.size()) {
                throw new DukeException("OOPS!!! The numbered task does not exist.");
            }
            return argument - 1;
        } catch (NumberFormatException numberFormatException) {
            throw new DukeException("OOPS!!! You did not enter the number corresponding to the task.");
        }
    }

    /**
     * Returns a valid int index of the Task
     * to be deleted from the TaskList.
     *
     * @param input    user input.
     * @param taskList list of Tasks that contains the Task to be deleted.
     * @return the index of the Task.
     * @throws DukeException when user input does not contain an int value after the command
     *                       or when the index provided does not correspond to any Task in the TaskList.
     */
    public int parseDelete(String input, TaskList taskList) throws DukeException {
        ArrayList<Task> tasks = taskList.getTasks();
        String[] arr = input.split(" ");
        if (arr.length < 2) {
            throw new DukeException("OOPS!!! You did not enter the number corresponding to the task.");
        }
        try {
            int argument = Integer.parseInt(arr[1]);
            if (argument < 1 || argument > tasks.size()) {
                throw new DukeException("OOPS!!! The numbered task does not exist.");
            }
            return argument - 1;
        } catch (NumberFormatException numberFormatException) {
            throw new DukeException("OOPS!!! You did not enter the number corresponding to the task.");
        }
    }

    /**
     * Returns a valid String keyword.
     *
     * @param input user input.
     * @return a valid String keyword
     * @throws DukeException when user enters more than one keyword
     *                       or none at all.
     */
    public String parseKeyword(String input) throws DukeException {
        String[] arr = input.split(" ");
        if (arr.length < 2) {
            throw new DukeException("OOPS!!! Please enter a keyword.");
        }
        if (arr.length > 2) {
            throw new DukeException("OOPS!!! Please enter only one keyword.");
        }
        return arr[1];
    }
}
