import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Parser {
    Parser() {}

    public static Command parseCommand(String input) throws DukeException {
        int endIndex = input.strip().indexOf(" ");
        String potentialCommand = (endIndex == -1) ? input : input.substring(0, endIndex);
        Command command;

        if (potentialCommand.toUpperCase().equals("TODO")) {
            command = Command.TODO;
        } else if (potentialCommand.toUpperCase().equals("DEADLINE")) {
            command = Command.DEADLINE;
        } else if (potentialCommand.toUpperCase().equals("EVENT")) {
            command = Command.EVENT;
        } else if (potentialCommand.toUpperCase().equals("LIST")) {
            command = Command.LIST;
        } else if (potentialCommand.toUpperCase().equals("DONE")) {
            command = Command.DONE;
        } else if (potentialCommand.toUpperCase().equals("BYE")) {
            command = Command.BYE;
        } else if (potentialCommand.toUpperCase().equals("DELETE")) {
            command = Command.DELETE;
        } else {
            throw new CommandNotFoundException("What do you mean? I do not know this command.");
        }
        return command;
    }

    public static int getDoneIndex(String input) throws DescriptionMissingException {
        String[] doneInstructions = input.strip().split(" ");

        if (doneInstructions.length != 2) {
            throw new DescriptionMissingException("Argument missing!");
        } else {
            try {
                String indexString = doneInstructions[1].strip();
                return Integer.parseInt(indexString) - 1;
            } catch (NumberFormatException e) {
                throw new DescriptionMissingException("Invalid argument!");
            }
        }
    }

    public static int getDeleteIndex(String input) throws DescriptionMissingException {
        String[] deleteInstructions = input.strip().split(" ");

        if (deleteInstructions.length != 2) {
            throw new DescriptionMissingException("Argument missing!");
        } else {
            try {
                String indexString = deleteInstructions[1].strip();
                return Integer.parseInt(indexString) - 1;
            } catch (NumberFormatException e) {
                throw new DescriptionMissingException("Invalid argument!");
            }
        }
    }

    public static Todo getTodo(String input) throws DescriptionMissingException {
        String name = input.substring(4).strip();
        if (name.equals("")) {
            throw new DescriptionMissingException("Argument missing!");
        } else {
            return new Todo(name);
        }
    }

    public static Deadline getDeadline(String input)
            throws DescriptionMissingException, InvalidDateTimeException {
        String nameDeadline = input.substring(8).strip();
        if (nameDeadline.equals("")) {
            throw new DescriptionMissingException("Argument missing!");
        }

        String[] nameAndDeadline = nameDeadline.split("/by");
        if (nameAndDeadline.length < 2) {
            throw new DescriptionMissingException("Argument missing!");
        }
        String name = nameAndDeadline[0].strip();
        String deadline = nameAndDeadline[1].strip();

        LocalDateTime cutOffTime = parseDateTime(deadline);
        return new Deadline(name, cutOffTime);
    }

    public static Event getEvent(String input) throws DescriptionMissingException, InvalidDateTimeException {
        String nameDate = input.substring(5).strip();
        if (nameDate.equals("")) {
            throw new DescriptionMissingException("Argument missing!");
        }

        String[] nameAndDate = nameDate.split("/at");
        if (nameAndDate.length < 2) {
            throw new DescriptionMissingException("Argument missing!");
        }
        String name = nameAndDate[0].strip();
        String Date = nameAndDate[1].strip();

        LocalDateTime startTime = parseDateTime(Date);
        return new Event(name, startTime);
    }

    public static LocalDateTime parseDateTime(String dateTime) throws InvalidDateTimeException {
        String[] dateAndTime = dateTime.strip().split(" ");
        LocalDateTime parsedDateTime;

        if (dateAndTime.length != 2) {
            throw new InvalidDateTimeException("Input date and time is not complete.");
        }
        String date = dateAndTime[0].strip();
        String time = dateAndTime[1].strip();
        parsedDateTime = LocalDateTime.of(parseDate(date), parseTime(time));
        return parsedDateTime;

    }

    public static LocalDate parseDate(String date) throws InvalidDateTimeException {
        String[] yearMonthDay = date.strip().split("-");
        if (yearMonthDay.length != 3) {
            throw new InvalidDateTimeException("Input date is not valid.");
        }
        String year = yearMonthDay[0].strip();
        String month = yearMonthDay[1].strip();
        String day = yearMonthDay[2].strip();
        if (year.length() == 4 && month.length() == 2 && day.length() == 2) {
            try {
                return LocalDate.parse(date);
            } catch (DateTimeException e) {
                throw new InvalidDateTimeException("Input date is not valid.");
            }
        } else {
            throw new InvalidDateTimeException("Input date is not valid.");
        }
    }

    public static LocalTime parseTime(String time) throws InvalidDateTimeException {
        String hour = time.strip().substring(0, 2);
        String minute = time.strip().substring(2, 4);
        try {
            return LocalTime.parse(hour + ":" + minute);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Input time is not valid.");
        }
    }
}
