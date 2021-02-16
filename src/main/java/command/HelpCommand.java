package command;

import task.TaskManager;
import util.DukeException;

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
    private final Optional<String> keywordOpt;
    private String message = "";

    private HelpCommand(String keyword) {
        this.keywordOpt = Optional.of(keyword);
    }

    private HelpCommand() {
        this.keywordOpt = Optional.empty();
    }

    public static HelpCommand fromCommandMap(HashMap<String, List<String>> commandMap)
            throws DukeException {
        List<String> keywords = commandMap.get(COMMAND_STRING);
        if (keywords.isEmpty()) {
            return new HelpCommand();
        }
        String keyword = keywords.get(0);
        boolean isValidKeyword = VALID_KEYWORDS.contains(keyword);
        if (isValidKeyword) {
            return new HelpCommand(keyword);
        } else {
            throw new DukeException("Help keyword is not valid");
        }
    }

    @Override
    public void execute(TaskManager taskManager) {
        message = keywordOpt.orElse(defaultHelpText());
    }

    private String defaultHelpText() {
        String validKeywordList = String.join(", ", VALID_KEYWORDS);

        String result = "Type \"help <keyword>\" to read detailed information about each command" +
                "\n" +
                "List of commands: \n" +
                validKeywordList;
        return result;
    }

    private String keywordHelpText(String keyword) {
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
            text = "";
            assert false; //Method should not have been called with an invalid keyword.
        }

        return preText + text;
    }

    private String listHelpText() {
        return "Usage: \"list\" - " +
                "Shows all outstanding tasks.";
    }

    private String findHelpText() {
        return "Usage: \"find <keyword1> <keyword2>...\" - " +
                "Shows all tasks whose description contains all of the keywords";
    }

    private String todoHelpText() {
        return "Usage: \"todo <description>\" - " +
                "Creates a Todo Task with the supplied description";
    }

    private String deadlineHelpText() {
        return "Usage: \"deadline <description> /by <YYYY-MM-DD>\" - " +
                "Creates a Deadline with the supplied description and date";
    }

    private String eventHelpText() {
        return "Usage: \"deadline <description> /at <YYYY-MM-DD>\" - " +
                "Creates an Event with the supplied description and date";
    }

    private String doneHelpText() {
        return "Usage: \"done <i>\" - " +
                "Marks the task with index i in the list as done";
    }

    private String deleteHelpText() {
        return "Usage: \"delete <i>\" - " +
                "Removes the task with index i from the list";
    }

    private String quitHelpText() {
        return "Usage: \"quit\" - Terminates the application";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
