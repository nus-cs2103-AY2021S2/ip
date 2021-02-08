package popo.parser;

import static popo.utils.Messages.MESSAGE_EMPTY_DESCRIPTION;
import static popo.utils.Messages.MESSAGE_ENTER_COMMAND;
import static popo.utils.Messages.MESSAGE_FOLLOW_USAGE;
import static popo.utils.Messages.MESSAGE_INDICATE_TASK;
import static popo.utils.Messages.MESSAGE_INVALID_COMMAND;
import static popo.utils.Messages.MESSAGE_INVALID_SYNTAX;
import static popo.utils.Messages.MESSAGE_INVALID_TASK_INDEX;
import static popo.utils.Messages.MESSAGE_MISSING_SEARCH_WORD;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import popo.commands.ByeCommand;
import popo.commands.Command;
import popo.commands.DeadlineCommand;
import popo.commands.DeleteCommand;
import popo.commands.DoneCommand;
import popo.commands.DurationCommand;
import popo.commands.EventCommand;
import popo.commands.FindCommand;
import popo.commands.HelpCommand;
import popo.commands.InvalidCommandException;
import popo.commands.InvalidDescriptionException;
import popo.commands.ListCommand;
import popo.commands.NoDescriptionException;
import popo.commands.PeriodCommand;
import popo.commands.ToDoCommand;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Regex to separate the command word and arguments.
     */
    public static final Pattern USER_COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into a command.
     *
     * @param userInput Full user input string.
     * @return A user command.
     * @throws InvalidCommandException     If the user pass in an unrecognized command.
     * @throws InvalidDescriptionException If the format of the arguments do not match the command.
     * @throws NoDescriptionException      If the arguments is empty when further information is required.
     */
    public Command parseCommand(String userInput) throws InvalidCommandException,
            InvalidDescriptionException, NoDescriptionException {
        Matcher matcher = USER_COMMAND_FORMAT.matcher(userInput.strip());

        if (!matcher.matches()) {
            throw new InvalidCommandException(MESSAGE_ENTER_COMMAND);
        }

        // Get the command word as captured by the named-capturing group
        String command = matcher.group("command");
        // Get the arguments as captured by the named-capturing group
        String arguments = matcher.group("arguments").strip();

        assert !command.isEmpty() : "The command word should not be empty at this point";

        switch (command) {
        case ToDoCommand.COMMAND_WORD:
            return parseArgumentsForToDo(arguments);
        case DeadlineCommand.COMMAND_WORD:
            return parseArgumentsForDeadline(arguments);
        case PeriodCommand.COMMAND_WORD:
            return parseArgumentsForPeriod(arguments);
        case EventCommand.COMMAND_WORD:
            return parseArgumentsForEvent(arguments);
        case DurationCommand.COMMAND_WORD:
            return parseArgumentsForDuration(arguments);
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case DoneCommand.COMMAND_WORD:
            return parseArgumentsForDone(arguments);
        case DeleteCommand.COMMAND_WORD:
            return parseArgumentsForDelete(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case FindCommand.COMMAND_WORD:
            return parseArgumentsForFind(arguments);
        default:
            throw new InvalidCommandException(MESSAGE_INVALID_COMMAND);
        }
    }

    /**
     * Parses the arguments for the todo command.
     *
     * @param arguments User input arguments string.
     * @return {@code ToDoCommand}.
     * @throws NoDescriptionException If the description of the task is empty.
     */
    private Command parseArgumentsForToDo(String arguments) throws NoDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException(MESSAGE_EMPTY_DESCRIPTION);
        }
        return new ToDoCommand(arguments);
    }

    /**
     * Parses the arguments for the deadline command.
     *
     * @param arguments User input arguments string.
     * @return {@code DeadlineCommand}.
     * @throws NoDescriptionException      If the description of the task is empty.
     * @throws InvalidDescriptionException If the format of the deadline is invalid.
     */
    private Command parseArgumentsForDeadline(String arguments) throws NoDescriptionException,
            InvalidDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException(MESSAGE_EMPTY_DESCRIPTION);
        }
        if (!arguments.contains("/by")) {
            throw new InvalidDescriptionException(MESSAGE_INVALID_SYNTAX + "\n"
                    + MESSAGE_FOLLOW_USAGE + "\n"
                    + DeadlineCommand.MESSAGE_USAGE);
        }
        String[] deadlineInputArr = arguments.split("/by", 2);
        assert deadlineInputArr.length == 2;
        String deadlineTaskName = deadlineInputArr[0].strip();
        String userInputDateTime = deadlineInputArr[1].strip();

        String[] userInputDateTimeArr = userInputDateTime.split("\\s*,*\\s+|\\s*,\\s*", 2);
        String userInputDate = userInputDateTimeArr[0].strip();
        LocalDate deadlineDate = ParserUtil.parseDate(userInputDate);

        if (userInputDateTimeArr.length == 1) {
            // User did not enter a time string
            return new DeadlineCommand(deadlineTaskName, deadlineDate);
        } else {
            assert userInputDateTimeArr.length == 2 : "There should be a time component string in the array";
            // User entered a time string
            String userInputTime = userInputDateTimeArr[1].strip();
            LocalTime deadlineTime = ParserUtil.parseTime(userInputTime);
            return new DeadlineCommand(deadlineTaskName, deadlineDate, deadlineTime);
        }
    }

    /**
     * Parses the arguments for the period command.
     *
     * @param arguments User input arguments string.
     * @return {@code PeriodCommand}.
     * @throws NoDescriptionException      If the description of the task is empty.
     * @throws InvalidDescriptionException If the formats of the dates are invalid.
     */
    private Command parseArgumentsForPeriod(String arguments) throws NoDescriptionException,
            InvalidDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException(MESSAGE_EMPTY_DESCRIPTION);
        }
        if (!arguments.contains("/start") || !arguments.contains("/end")) {
            throw new InvalidDescriptionException(MESSAGE_INVALID_SYNTAX + "\n"
                    + MESSAGE_FOLLOW_USAGE + "\n"
                    + PeriodCommand.MESSAGE_USAGE);
        }
        String[] periodInputArr = arguments.split("/start", 2);
        assert periodInputArr.length == 2;
        String periodTaskName = periodInputArr[0].strip();
        String dates = periodInputArr[1].strip();

        String[] datesArr = dates.split("/end", 2);
        assert datesArr.length == 2;
        String startDateString = datesArr[0].strip();
        String endDateString = datesArr[1].strip();
        LocalDate startDate = ParserUtil.parseDate(startDateString);
        LocalDate endDate = ParserUtil.parseDate(endDateString);
        return new PeriodCommand(periodTaskName, startDate, endDate);
    }

    /**
     * Parses the arguments for the event command.
     *
     * @param arguments User input arguments string.
     * @return {@code EventCommand}.
     * @throws NoDescriptionException      If the description of the task is empty.
     * @throws InvalidDescriptionException If the format of the event time is invalid.
     */
    private Command parseArgumentsForEvent(String arguments) throws NoDescriptionException,
            InvalidDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException(MESSAGE_EMPTY_DESCRIPTION);
        }
        if (!arguments.contains("/at")) {
            throw new InvalidDescriptionException(MESSAGE_INVALID_SYNTAX + "\n"
                    + MESSAGE_FOLLOW_USAGE + "\n"
                    + EventCommand.MESSAGE_USAGE);
        }
        String[] eventInputArr = arguments.split("/at", 2);
        assert eventInputArr.length == 2;
        String eventTaskName = eventInputArr[0].strip();
        String eventTime = eventInputArr[1].strip();
        return new EventCommand(eventTaskName, eventTime);
    }

    /**
     * Parses the arguments for the duration command.
     *
     * @param arguments User input arguments string.
     * @return {@code DurationCommand}.
     * @throws NoDescriptionException      If the description of the task is empty.
     * @throws InvalidDescriptionException If the format of the duration is invalid.
     */
    private Command parseArgumentsForDuration(String arguments) throws NoDescriptionException,
            InvalidDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException(MESSAGE_EMPTY_DESCRIPTION);
        }
        if (!arguments.contains("/days") && !arguments.contains("/hours") && !arguments.contains("/minutes")) {
            throw new InvalidDescriptionException(MESSAGE_INVALID_SYNTAX + "\n"
                    + MESSAGE_FOLLOW_USAGE + "\n"
                    + DurationCommand.MESSAGE_USAGE);
        }
        try {
            String[] durationInputArr;
            TemporalUnit unit;
            if (arguments.contains("/days")) {
                durationInputArr = arguments.split("/days", 2);
                unit = ChronoUnit.DAYS;
            } else if (arguments.contains("/hours")) {
                durationInputArr = arguments.split("/hours", 2);
                unit = ChronoUnit.HOURS;
            } else {
                assert arguments.contains("/minutes");
                durationInputArr = arguments.split("/minutes", 2);
                unit = ChronoUnit.MINUTES;
            }
            assert durationInputArr != null;
            assert durationInputArr.length == 2;
            assert unit != null;
            String durationTaskName = durationInputArr[0].strip();
            String durationString = durationInputArr[1].strip();
            long amount = Long.parseLong(durationString);
            Duration duration = Duration.of(amount, unit);
            return new DurationCommand(durationTaskName, duration);
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException("Please enter a valid number.");
        }
    }

    /**
     * Parses the arguments for the done command.
     *
     * @param arguments User input arguments string.
     * @return {@code DoneCommand}.
     * @throws InvalidDescriptionException If the description is not a valid index.
     * @throws NoDescriptionException      If the task number is missing.
     * @throws IndexOutOfBoundsException   If the specified task number is outside of range.
     */
    private Command parseArgumentsForDone(String arguments) throws InvalidDescriptionException,
            NoDescriptionException, IndexOutOfBoundsException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException(MESSAGE_INDICATE_TASK);
        }
        try {
            int index = Integer.parseInt(arguments.strip()) - 1; // Account for 0-based indexing
            return new DoneCommand(index);
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException(MESSAGE_INVALID_TASK_INDEX);
        }
    }

    /**
     * Parses the arguments for the delete command.
     *
     * @param arguments User input arguments string.
     * @return {@code DeleteCommand}.
     * @throws InvalidDescriptionException If the description is not a valid index.
     * @throws NoDescriptionException      If the task number is missing.
     * @throws IndexOutOfBoundsException   If the specified task number is outside of range.
     */
    private Command parseArgumentsForDelete(String arguments) throws InvalidDescriptionException,
            NoDescriptionException, IndexOutOfBoundsException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException(MESSAGE_INDICATE_TASK);
        }
        try {
            int index = Integer.parseInt(arguments.strip()) - 1; // Account for 0-based indexing
            return new DeleteCommand(index);
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException(MESSAGE_INVALID_TASK_INDEX);
        }
    }

    /**
     * Parses the arguments for the find command.
     *
     * @param arguments User input arguments string.
     * @return {@code FindCommand}.
     * @throws NoDescriptionException If the search word is missing.
     */
    private Command parseArgumentsForFind(String arguments) throws NoDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException(MESSAGE_MISSING_SEARCH_WORD);
        }
        return new FindCommand(arguments);
    }
}
