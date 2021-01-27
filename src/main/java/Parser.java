import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {

    public static DateTimeFormatter INPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public static Command parseCommand(String input) throws DukeException {
        String[] arr = input.split(" ");
        try {
            return Command.valueOf(arr[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! I'm sorry, but that is an invalid command :-(");
        }
    }

    public static ToDo parseToDo(String input) throws DukeException {
        String[] arr = input.split(" ");
        if (arr.length < 2) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new ToDo(input.substring(5));
    }

    public static Deadline parseDeadline(String input) throws DukeException {
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
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Please input date and time in the following format: dd-MM-yyyy HHmm.");
        }
        return new Deadline(arr2[0].substring(9), deadlineDate);
    }

    public static Event parseEvent(String input) throws DukeException {
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
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Please input date and time in the following format: dd-MM-yyyy HHmm.");
        }
        return new Event(arr3[0].substring(6), eventDate);
    }

    public static int parseDone(String input, ArrayList<Task> tasks) throws DukeException {
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
        } catch (NumberFormatException ex) {
            throw new DukeException("OOPS!!! You did not enter the number corresponding to the task.");
        }
    }

    public static int parseDelete(String input, ArrayList<Task> tasks) throws DukeException {
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
        } catch (NumberFormatException exception) {
            throw new DukeException("OOPS!!! You did not enter the number corresponding to the task.");
        }
    }
}
