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
    public void execute(TaskManager taskManager) throws DukeException {
        message = defaultHelpText();
    }

    private String defaultHelpText() {
        String validKeywordList = String.join(", ", VALID_KEYWORDS);

        StringBuilder result = new StringBuilder();
        result.append("Type \"help <keyword>\" to read detailed information about each command")
                .append("\n")
                .append("List of keywords: \n")
                .append(validKeywordList);

        return result.toString();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
