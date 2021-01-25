import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    Parser() {}

    public static Command parseCommand(String input) throws DukeException {
        int endIndex = input.indexOf(" ");
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
        if (input.length() < 6 || !Character.isDigit(input.charAt(5))) {
            throw new DescriptionMissingException("The index of the task done?");
        } else {
            return Character.getNumericValue(input.charAt(5)) - 1;
        }
    }

    public static int getDeleteIndex(String input) throws DescriptionMissingException {
        if (input.length() < 8 || !Character.isDigit(input.charAt(7))) {
            throw new DescriptionMissingException("The index of the deleting task?");
        } else {
            return Character.getNumericValue(input.charAt(7)) - 1;
        }
    }

    public static Todo getTodo(String input) throws DescriptionMissingException {
        if (input.length() < 6) {
            throw new DescriptionMissingException("No description?");
        } else {
            String description = input.substring(5);
            return new Todo(description);
        }
    }

    public static Deadline getDeadline(String input)
            throws DescriptionMissingException, InvalidDateTimeException {
        if (input.length() < 10) {
            throw new DescriptionMissingException("No description?");
        } else {
            String description = input.substring(9);
            int index = description.indexOf(" /by");
            if (index == -1) {
                throw new DescriptionMissingException("Invalid input");
            } else {
                try {
                    String name = description.substring(0, index);
                    String deadline = description.substring(index + 5);
                    LocalDateTime cutOffTime = parseDateTime(deadline);
                    return new Deadline(name, cutOffTime);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DescriptionMissingException("Missing description?");
                }
            }
        }
    }

    public static Event getEvent(String input) throws DescriptionMissingException, InvalidDateTimeException {
        if (input.length() < 7) {
            throw new DescriptionMissingException("No description?");
        } else {
            String description = input.substring(6);
            int index = description.indexOf(" /at");
            if (index == -1) {
                throw new DescriptionMissingException("Invalid input");
            } else {
                try {
                    String name = description.substring(0, index);
                    String time = description.substring(index + 5);
                    LocalDateTime startTime = parseDateTime(time);
                    return new Event(name, startTime);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DescriptionMissingException("Missing description?");
                }
            }
        }
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
