package command;

import task.TaskManager;
import task.Todo;
import util.DukeException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TodoCommand implements Command {
    public static final String COMMAND_STRING = "todo";
    public static final CommandType COMMAND_TYPE = CommandType.TODO;
    private final String description;
    private String message;

    private TodoCommand(String description) {
        this.description = description;
    }

    public static TodoCommand fromCommandMap(HashMap<String, List<String>> commandMap)
            throws DukeException {
        List<String> descriptionStrings = commandMap.get(COMMAND_STRING);
        if (descriptionStrings.isEmpty()) {
            throw new DukeException("Todo description cannot be empty");
        }

        String description = String.join(" ", descriptionStrings);
        return new TodoCommand(description);
    }

    @Override
    public void execute(TaskManager taskManager) {
        message = taskManager.addTask(new Todo(description));
    }

    @Override
    public String getMessage() {
        return message;
    }
}
