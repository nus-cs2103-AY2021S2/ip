import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public Parser() {

    }

    public Command parseCommand(String userInput) {
        if (userInput.equals("bye")) {
            return Command.BYE;
        } else if (userInput.equals("list")) {
            return Command.LIST;
        } else if (userInput.length() >= 4 && userInput.startsWith("done")) {
            return Command.DONE;
        } else if (userInput.length() >= 4 && userInput.startsWith("todo")) {
            return Command.TODO;
        } else if (userInput.length() >= 5 && userInput.startsWith("event")) {
            return Command.EVENT;
        } else if (userInput.length() >= 8 && userInput.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (userInput.length() >= 6 && userInput.startsWith("delete")) {
            return Command.DELETE;
        } else {
            return Command.INVALID;
        }
    }

    public int parseNumber(String userInput, int index) throws DukeException {
        int number;
        try {
            String indexString = userInput.substring(index).strip();
            number = Integer.parseInt(indexString);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Remember to specify which task you are done with " +
                    "using a valid number", e);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid format, please try again using numbers only.", e);
        }
        return number;
    }

    public String parseName(String userInput, int start) {
        return userInput.substring(start);
    }

    public Event parseEvent(String userInput, int timeIndex) throws DukeException {
        try {
            String name = userInput.substring(6, timeIndex - 1);
            String dateTime = userInput.substring(timeIndex + 4);
            String dateString = dateTime.substring(0, 10);
            String timeString = dateTime.substring(11);
            LocalDate date = LocalDate.parse(dateString);
            return new Event(name, date, timeString);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description of an event includes date and time.", e);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter the date in the proper format: yyyy-mm-dd", e);
        }
    }

    public Deadline parseDeadline(String userInput, int timeIndex) throws DukeException {
        try {
            String name = userInput.substring(9, timeIndex - 1);
            String dateTime = userInput.substring(timeIndex + 4);
            String dateString = dateTime.substring(0, 10);
            String timeString = dateTime.substring(11);
            LocalDate deadline = LocalDate.parse(dateString);
            return new Deadline(name, deadline, timeString);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description of a deadline includes date and time.", e);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter the date in the proper format: yyyy-mm-dd", e);
        }
    }
}
