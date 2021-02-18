package command;

import task.TaskManager;

import java.util.HashMap;
import java.util.List;

public class QuitCommand extends Command {
    public static final String COMMAND_STRING = "quit";
    public static final CommandType COMMAND_TYPE = CommandType.QUIT;

    public static QuitCommand fromCommandMap(HashMap<String, List<String>> commandMap) {
        return new QuitCommand();
    }

    @Override
    public String execute(TaskManager taskManager) {
        return "See you again soon!";
    }

    @Override
    public boolean isQuitCommand() {
        return true;
    }
}
