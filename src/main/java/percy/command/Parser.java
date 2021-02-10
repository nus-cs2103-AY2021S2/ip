package percy.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import percy.exception.DateTimeParsingException;
import percy.exception.EmptyArgumentsException;
import percy.exception.InvalidArgumentsException;
import percy.exception.PercyException;

public class Parser {
    private static String fullCmd;

    public Parser(String fullCmd) {
        this.fullCmd = fullCmd.trim().strip();
    }

    public Command getCommand() throws PercyException {
        String command = this.fullCmd.split(" ", 2)[0];
        switch (command) {
        case TodoCommand.COMMAND:
            return new TodoCommand(getTodoDescription());
        case EventCommand.COMMAND:
            return new EventCommand(getEventDescription(), getEventDate(), getEventTime());
        case DeadlineCommand.COMMAND:
            return new DeadlineCommand(getDeadlineDescription(), getDeadlineDate(), getDeadlineTime());
        case DoneCommand.COMMAND:
            return new DoneCommand(getTaskNumber(DoneCommand.COMMAND));
        case ListCommand.COMMAND:
            return new ListCommand();
        case DeleteCommand.COMMAND:
            return new DeleteCommand(getTaskNumber(DeleteCommand.COMMAND));
        case ByeCommand.COMMAND:
            return new ByeCommand();
        case FindCommand.COMMAND:
            return new FindCommand(getKeywords());
        case HelpCommand.COMMAND:
            return new HelpCommand(getHelpWord());
        default:
            return new UnknownCommand();
        }
    }



    private static int getTaskNumber(String commandType) throws PercyException {
        String[] splitCommand = fullCmd.split(" ", 2);
        String taskNumber;
        try {
            taskNumber = splitCommand[1].trim();
            return Integer.parseInt(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyArgumentsException(commandType);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException(commandType);
        }
    }

    private static Optional<String> getHelpWord() {
        String[] splitCommand = fullCmd.split(" ", 2);
        Optional<String> helpWord;
        try {
            helpWord = Optional.ofNullable(splitCommand[1].trim());
        } catch (IndexOutOfBoundsException e) {
            helpWord = Optional.ofNullable(null);
        }
        return helpWord;
    }

    private static String getTodoDescription() throws PercyException {
        String[] splitCommand = fullCmd.split(" ", 2);
        String description;
        try {
            description = splitCommand[1];
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyArgumentsException(TodoCommand.COMMAND);
        }
        return description;
    }

    private static String getEventDescription() throws PercyException {
        try {
            String[] splitCommand = fullCmd.split(" ", 2);
            String[] args = splitCommand[1].split(EventCommand.DATE_TIME_PREFIX, 2);
            String description = args[0].trim();
            return description;
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyArgumentsException(EventCommand.COMMAND);
        }
    }

    private static LocalDate getEventDate() throws PercyException {
        try {
            String[] splitCommand = fullCmd.split(" ", 2);
            String[] args = splitCommand[1].split(EventCommand.DATE_TIME_PREFIX, 2);
            String[] dateTime = args[1].trim().split(" ", 2);
            LocalDate date = LocalDate.parse(dateTime[0].trim());
            return date;
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyArgumentsException(EventCommand.COMMAND);
        } catch (DateTimeParseException e) {
            throw new DateTimeParsingException(EventCommand.COMMAND);
        }
    }

    private static LocalTime getEventTime() throws PercyException {
        try {
            String[] splitCommand = fullCmd.split(" ", 2);
            String[] args = splitCommand[1].split(EventCommand.DATE_TIME_PREFIX, 2);
            String[] dateTime = args[1].trim().split(" ", 2);
            LocalTime time = LocalTime.parse(dateTime[1].trim(), DateTimeFormatter.ofPattern("HHmm"));
            return time;
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyArgumentsException(EventCommand.COMMAND);
        } catch (DateTimeParseException e) {
            throw new DateTimeParsingException(EventCommand.COMMAND);
        }
    }

    private static String getDeadlineDescription() throws PercyException {
        try {
            String[] splitCommand = fullCmd.split(" ", 2);
            String[] args = splitCommand[1].split(DeadlineCommand.DATE_TIME_PREFIX, 2);
            String description = args[0].trim();
            assert !description.isEmpty();
            return description;
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyArgumentsException(DeadlineCommand.COMMAND);
        }
    }

    private static LocalDate getDeadlineDate() throws PercyException {
        try {
            String[] splitCommand = fullCmd.split(" ", 2);
            String[] args = splitCommand[1].split(DeadlineCommand.DATE_TIME_PREFIX, 2);
            String[] dateTime = args[1].trim().split(" ", 2);
            LocalDate date = LocalDate.parse(dateTime[0].trim());
            return date;
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyArgumentsException(DeadlineCommand.COMMAND);
        } catch (DateTimeParseException e) {
            throw new DateTimeParsingException(DeadlineCommand.COMMAND);
        }
    }

    private static LocalTime getDeadlineTime() throws PercyException {
        try {
            String[] splitCommand = fullCmd.split(" ", 2);
            String[] args = splitCommand[1].split(DeadlineCommand.DATE_TIME_PREFIX, 2);
            String[] dateTime = args[1].trim().split(" ", 2);
            LocalTime time = LocalTime.parse(dateTime[1].trim(), DateTimeFormatter.ofPattern("HHmm"));
            return time;
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyArgumentsException(DeadlineCommand.COMMAND);
        } catch (DateTimeParseException e) {
            throw new DateTimeParsingException(DeadlineCommand.COMMAND);
        }
    }

    private static String[] getKeywords() throws PercyException {
        try {
            String[] splitCommand = fullCmd.split(" ", 2);
            return splitCommand[1].trim().toLowerCase().split(" ");
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyArgumentsException(FindCommand.COMMAND);
        }
    }
}
