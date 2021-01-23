import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static Command parse(String text) throws DukeException {
        if(text.matches("^todo($|.+$)")) {
            return parseToDo(text);
        } else if(text.matches("^deadline($|.+$)")) {
            return parseDeadline(text);
        } else if(text.matches("^event($|.+$)")) {
            return parseEvent(text);
        } else if(text.matches("^done($|.+$)")) {
            return parseDone(text);
        } else if(text.matches("^delete($|.+$)")) {
            return parseDelete(text);
        } else if(text.equals("list")) {
            return parseList(text);
        } else if(text.equals("bye")) {
            return parseExit(text);
        } else {
            throw new DukeException("No such command, please try again with another command.");
        }
    }

    public static ToDoCommand parseToDo(String text) throws DukeCommandException {
        String desc = text.substring(4).stripLeading();
        if(desc.length() == 0) {
            throw new DukeCommandException("todo", desc, "Description of ToDo cannot be empty");
        } else {
            return new ToDoCommand(desc);
        }
    }

    public static DeadlineCommand parseDeadline(String text) throws DukeCommandException {
        String params = text.substring(8).stripLeading();
        if(params.length() == 0) {
            throw new DukeCommandException("deadline", params, "The details of a Deadline cannot be empty.");
        } else if(!params.contains("/by") || params.split(" /by ").length != 2) {
            throw new DukeCommandException("deadline", params, "Description and date/time must be given for a " +
                    "Deadline.");
        } else if(!params.split(" /by ")[1].matches("^(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[0-2])-" +
                "([1-9][0-9][0-9][0-9]) ([1-9]|1[0-2])(AM|PM)")) {
            throw new DukeCommandException("deadline", params, "Date time format is incorrect, try to follow the " +
                    "format of dd-mm-yyyy hAM/PM.");
        } else {
            String[] splits = params.split(" /by ");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");
            LocalDateTime dateTime = LocalDateTime.parse(splits[1], formatter);

            return new DeadlineCommand(splits[0], dateTime);
        }
    }

    public static EventCommand parseEvent(String text) throws DukeCommandException {
        String startEndPattern = "^(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[0-2])-" +
                "([1-9][0-9][0-9][0-9]) ([1-9]|1[0-2])(AM|PM)";

        if(text.length() == 0) {
            throw new DukeCommandException("event", text, "The details of a Event cannot be empty.");
        } else if(!text.contains("/start") || !text.contains("/end") || text.split(" /start | /end ").length != 3) {
            throw new DukeCommandException("event", text, "Description, start datetime, and end datetime " +
                    "must be given for an Event.");
        } else if(!text.split(" /start | /end ")[1].matches(startEndPattern)
                || !text.split(" /start | /end ")[2].matches(startEndPattern)) {
            throw new DukeCommandException("deadline", text, "Start or end date has incorrect format, try to " +
                    "follow the format of dd-mm-yyyy hAM/PM.");
        } else {
            String[] splits = text.split(" /start | /end ");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");
            LocalDateTime start = LocalDateTime.parse(splits[1], formatter);
            LocalDateTime end = LocalDateTime.parse(splits[2], formatter);

            return new EventCommand(splits[0], start, end);
        }
    }

    public static DoneCommand parseDone(String text) throws DukeCommandException {
        if(!text.matches("-?(0|[1-9]\\d*)")) {
            throw new DukeCommandException("done", text, "Please provide an actual number for the task you are done " +
                    "with.");
        } else {
            return new DoneCommand(Integer.parseInt(text) - 1);
        }
    }

    public static DeleteCommand parseDelete(String text) throws DukeCommandException {
        if(!text.matches("-?(0|[1-9]\\d*)")) {
            throw new DukeCommandException("delete", text, "Please provide an actual number for the task you are " +
                    "deleting.");
        } else {
            return new DeleteCommand(Integer.parseInt(text) - 1);
        }
    }

    public static ListCommand parseList(String text) {
        return new ListCommand();
    }

    public static ExitCommand parseExit(String text) {
        return new ExitCommand();
    }
}
