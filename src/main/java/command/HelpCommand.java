package command;

import task.TaskManager;
import util.Formatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    private HelpCommand(String keyword) {
        this.response = Optional.ofNullable(keyword)
                .map(HelpCommand::keywordHelpText)
                .orElse(defaultHelpText());
    }

    public static HelpCommand fromCommandMap(HashMap<String, List<String>> commandMap) {
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
        return "Usage: \"deadline <description> /at <YYYY-MM-DD>\" - "
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

    @Override
    public String execute(TaskManager taskManager) {
        return response;
    }
}
