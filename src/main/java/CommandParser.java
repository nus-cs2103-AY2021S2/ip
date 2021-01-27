import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {

    private static final Pattern COMMAND_FORMAT = Pattern.compile("\\W*(\\S+)\\W*(.*)");

    /**
     * Parses input from String class to Command class.
     *
     * @param input User string input
     * @return Command object with respective details.
     */
    public static Command parse(String input) {
        Matcher matcher = COMMAND_FORMAT.matcher(input);
        if (!matcher.matches()) {
            return new HelpCommand(true);
        }

        String command = matcher.group(1).trim();
        String arguments = matcher.group(2).trim();

        try {
            switch (command.toLowerCase()) {
            case ToDoCommand.COMMAND:
                return ToDoCommand.parseArguments(arguments);
            case EventCommand.COMMAND:
                return EventCommand.parseArguments(arguments);
            case DeadlineCommand.COMMAND:
                return DeadlineCommand.parseArguments(arguments);
            case ListCommand.COMMAND:
                return new ListCommand();
            case DoneCommand.COMMAND:
                return DoneCommand.parseArguments(arguments);
            case DeleteCommand.COMMAND:
                return DeleteCommand.parseArguments(arguments);
            case HelpCommand.COMMAND:
                return new HelpCommand(false);
            case ByeCommand.COMMAND:
                return new ByeCommand();
            case FindCommand.COMMAND:
                return FindCommand.parseArguments(arguments);
            default:
                return new HelpCommand(true);
            }
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
