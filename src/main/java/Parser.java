import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Parser {
    // Regex to separate command word and arguments
    public static final Pattern USER_COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

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
        case HelpCommand.COMMAND_WORD:  // Fallthrough
        default:
            return new HelpCommand();
        }
    }

    private Command parseArgumentsForToDo(String arguments) {
        return new ToDoCommand(arguments);
    }

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

    private static LocalDateTime parseDateTime(String dateString) throws InvalidDescriptionException {
        try {
            return LocalDateTime.parse(dateString, Formatter.INPUT_DATE_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new InvalidDescriptionException("Please enter a valid date and time for a deadline task " +
                    "using this format:\ndeadline task_name /by dd/mm/yyyy HHHH");
        }
    }

    private Command parseArgumentsForEvent(String arguments) {
        String[] eventInputArr = arguments.split("/at");
        String eventTaskName = eventInputArr[0].strip();
        String eventTime = eventInputArr[1].strip();
        return new EventCommand(eventTaskName, eventTime);
    }

    private Command parseArgumentsForDone(String arguments) throws NoDescriptionException,
            InvalidDescriptionException, IndexOutOfBoundsException {
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

    private Command parseArgumentsForDelete(String arguments) throws NoDescriptionException,
            InvalidDescriptionException, IndexOutOfBoundsException {
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

//    public static Command parseCommand(String userInputCommand) throws InvalidCommandException {
//        try {
//            return Command.valueOf(userInputCommand.toUpperCase());
//        } catch (IllegalArgumentException e) {
//            throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
//        }
//        
//        
//    }
}
