package duke.parser;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.InvalidCommandException;
import duke.commands.InvalidDescriptionException;
import duke.commands.ListCommand;
import duke.commands.NoDescriptionException;
import duke.commands.ToDoCommand;

import duke.utils.Formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * @param userInput full user input string
     * @return user command
     * @throws InvalidCommandException     if the user pass in an unrecognized command
     * @throws InvalidDescriptionException if the format of the arguments do not match the command
     * @throws NoDescriptionException      if the arguments is empty when further information is required for the command
     */
    public Command parseCommand(String userInput) throws InvalidCommandException,
            InvalidDescriptionException, NoDescriptionException {
        Matcher matcher = USER_COMMAND_FORMAT.matcher(userInput.strip());

        if (!matcher.matches()) {
            throw new InvalidCommandException("Please enter a valid command!!!");
        }

        // Get the command word as captured by the named-capturing group
        String command = matcher.group("command");
        // Get the arguments as captured by the named-capturing group
        String arguments = matcher.group("arguments").strip();

        switch (command) {
        case ToDoCommand.COMMAND_WORD:
            return parseArgumentsForToDo(arguments);
        case DeadlineCommand.COMMAND_WORD:
            return parseArgumentsForDeadline(arguments);
        case EventCommand.COMMAND_WORD:
            return parseArgumentsForEvent(arguments);
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
            throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the arguments for the todo command.
     *
     * @param arguments user input arguments string
     * @return {@code ToDoCommand}
     * @throws NoDescriptionException if the description of the task is empty
     */
    private Command parseArgumentsForToDo(String arguments) throws NoDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException("OOPS!!! The description of a task cannot be empty.");
        }
        return new ToDoCommand(arguments);
    }

    /**
     * Parses the arguments for the deadline command.
     *
     * @param arguments user input arguments string
     * @return {@code DeadlineCommand}
     * @throws InvalidDescriptionException if the format of the date and time is invalid
     * @throws NoDescriptionException      if the description of the task is empty
     * @see Parser#parseDateTime(String)
     */
    private Command parseArgumentsForDeadline(String arguments) throws InvalidDescriptionException,
            NoDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException("OOPS!!! The description of a task cannot be empty.");
        }
        String[] deadlineInputArr = arguments.split("/by");
        String deadlineTaskName = deadlineInputArr[0].strip();
        String userInputDateTime = deadlineInputArr[1].strip();
        LocalDateTime deadline = parseDateTime(userInputDateTime);
        return new DeadlineCommand(deadlineTaskName, deadline);
    }

    /**
     * Parses the input date string format into a LocalDateTime object.
     *
     * @param dateString user input date of the format "dd/mm/yyyy HHHH"
     * @return {@code LocalDateTime} object representing the date and time
     * @throws InvalidDescriptionException if the format of the date and time is invalid
     */
    private static LocalDateTime parseDateTime(String dateString) throws InvalidDescriptionException {
        try {
            return LocalDateTime.parse(dateString, Formatter.INPUT_DATE_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new InvalidDescriptionException("Please enter a valid date and time for a deadline task "
                    + "using this format:\ndeadline task_name /by dd/mm/yyyy HHHH");
        }
    }

    /**
     * Parses the arguments for the event command.
     *
     * @param arguments user input arguments string
     * @return {@code EventCommand}
     * @throws NoDescriptionException if the description of the task is empty
     */
    private Command parseArgumentsForEvent(String arguments) throws NoDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException("OOPS!!! The description of a task cannot be empty.");
        }
        String[] eventInputArr = arguments.split("/at");
        String eventTaskName = eventInputArr[0].strip();
        String eventTime = eventInputArr[1].strip();
        return new EventCommand(eventTaskName, eventTime);
    }

    /**
     * Parses the arguments for the done command.
     *
     * @param arguments user input arguments string
     * @return {@code DoneCommand}
     * @throws InvalidDescriptionException if the description is not a valid index
     * @throws NoDescriptionException      if the description of the task is empty
     * @throws IndexOutOfBoundsException   if the specified task number is outside of range
     */
    private Command parseArgumentsForDone(String arguments) throws InvalidDescriptionException,
            NoDescriptionException, IndexOutOfBoundsException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException("Please indicate a task number to be marked as done.");
        }
        try {
            int index = Integer.parseInt(arguments.strip()) - 1;  // Account for 0-based indexing
            return new DoneCommand(index);
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException("Please enter a valid task number");
        }
    }

    /**
     * Parses the arguments for the delete command.
     *
     * @param arguments user input arguments string
     * @return {@code DeleteCommand}
     * @throws InvalidDescriptionException if the description is not a valid index
     * @throws NoDescriptionException      if the description of the task is empty
     * @throws IndexOutOfBoundsException   if the specified task number is outside of range
     */
    private Command parseArgumentsForDelete(String arguments) throws InvalidDescriptionException,
            NoDescriptionException, IndexOutOfBoundsException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException("Please indicate a task number to be marked as done.");
        }
        try {
            int index = Integer.parseInt(arguments.strip()) - 1;  // Account for 0-based indexing
            return new DeleteCommand(index);
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException("Please enter a valid task number");
        }
    }

    private Command parseArgumentsForFind(String arguments) throws NoDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException("Please enter a search word or phrase!");
        }
        return new FindCommand(arguments);
    }
}
