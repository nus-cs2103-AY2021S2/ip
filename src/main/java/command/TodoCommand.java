package command;

import task.TaskManager;
import task.Todo;
import util.DukeException;

import java.util.HashMap;
import java.util.List;

public class TodoCommand extends Command {
    public static final String COMMAND_STRING = "todo";
    public static final CommandType COMMAND_TYPE = CommandType.TODO;
    private final String description;

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
    public String execute(TaskManager taskManager) {
        return taskManager.addTask(new Todo(description));
    }


}
