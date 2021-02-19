package command;

import task.TaskManager;
import util.Formatter;
import util.Parser;

import java.util.*;

/**
 * Command to display help text.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_STRING = "help";
    public static final CommandType COMMAND_TYPE = CommandType.HELP;
    private static final List<String> VALID_KEYWORDS = new ArrayList<>() {{
        add(ListCommand.COMMAND_STRING);
        add(FindCommand.COMMAND_STRING);
        add(TodoCommand.COMMAND_STRING);
        add(DeadlineCommand.COMMAND_STRING);
        add(EventCommand.COMMAND_STRING);
        add(DoneCommand.COMMAND_STRING);
        add(DeleteCommand.COMMAND_STRING);
        add(QuitCommand.COMMAND_STRING);
    }};
    private final String response;

    /**
     * Creates a HelpCommand that would return help text to explain the commands
     * available in the application. Depending on the keyword supplied, the help
     * text will differ accordingly.
     *
     * @param keyword The type of help to be displayed. Nullable.
     */
    private HelpCommand(String keyword) {
        // If the keyword is null, return the defaultHelpText.
        this.response = Optional.ofNullable(keyword)
                .map(HelpCommand::keywordHelpText)
                .orElse(defaultHelpText());
    }

    /**
     * Constructs a HelpCommand from a commandMap.
     *
     * @param commandMap CommandMap representing the instruction.
     * @return HelpCommand object based on the commandMap.
     */
    public static HelpCommand fromCommandMap(HashMap<String, List<String>> commandMap) {
        assert Parser.extractCommandString(commandMap).equals(COMMAND_STRING)
                : COMMAND_STRING + "CommandFlag does not match";

        List<String> keywords = commandMap.get(COMMAND_STRING);
        if (keywords.isEmpty()) {
            return new HelpCommand(null);
        } else {
            String keyword = keywords.get(0);
            return new HelpCommand(keyword);
        }
    }

    private static String defaultHelpText() {

        return "Type \"help <command>\" to read detailed information about each command"
                + "\n"
                + "List of commands: \n"
                + Formatter.formatList(VALID_KEYWORDS);
    }

    /**
     * Returns help text based on the keyword that was supplied.
     *
     * @param keyword The keyword to show help text for.
     * @return Keyword-based help text.
     */
    private static String keywordHelpText(String keyword) {
        String preText = "Help for \"" + keyword + "\":\n";
        String text;

        switch (keyword) {
        case ListCommand.COMMAND_STRING:
            text = listHelpText();
            break;
        case FindCommand.COMMAND_STRING:
            text = findHelpText();
            break;
        case TodoCommand.COMMAND_STRING:
            text = todoHelpText();
            break;
        case DeadlineCommand.COMMAND_STRING:
            text = deadlineHelpText();
            break;
        case EventCommand.COMMAND_STRING:
            text = eventHelpText();
            break;
        case DoneCommand.COMMAND_STRING:
            text = doneHelpText();
            break;
        case DeleteCommand.COMMAND_STRING:
            text = deleteHelpText();
            break;
        case QuitCommand.COMMAND_STRING:
            text = quitHelpText();
            break;
        default:
            return "Please a valid command for \"help <command>\"";
        }

        return preText + text;
    }

    private static String listHelpText() {
        return "Usage: \"list\" - "
                + "Shows all outstanding tasks.";
    }

    private static String findHelpText() {
        return "Usage: \"find <keyword1> <keyword2>...\" - "
                + "Shows all tasks whose description contains all of the keywords";
    }

    private static String todoHelpText() {
        return "Usage: \"todo <description>\" - "
                + "Creates a Todo Task with the supplied description";
    }

    private static String deadlineHelpText() {
        return "Usage: \"deadline <description> /by <YYYY-MM-DD>\" - "
                + "Creates a Deadline with the supplied description and date";
    }

    private static String eventHelpText() {
        return "Usage: \"event <description> /at <YYYY-MM-DD>\" - "
                + "Creates an Event with the supplied description and date";
    }

    private static String doneHelpText() {
        return "Usage: \"done <i>\" - "
                + "Marks the task with index i in the list as done";
    }

    private static String deleteHelpText() {
        return "Usage: \"delete <i>\" - "
                + "Removes the task with index i from the list";
    }

    private static String quitHelpText() {
        return "Usage: \"quit\" - Terminates the application";
    }

    /**
     * Returns the help text.
     *
     * @param taskManager Does not perform any action on the TaskManager. Still
     *                    requires an input for class abstraction.
     * @return Help text.
     */
    @Override
    public String execute(TaskManager taskManager) {
        return response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HelpCommand that = (HelpCommand) o;
        return Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(response);
    }
}
