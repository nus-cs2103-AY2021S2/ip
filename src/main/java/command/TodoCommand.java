package command;

import task.TaskManager;
import task.Todo;
import util.DukeException;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Command to add a Todo Task to a TaskManager.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_STRING = "todo";
    public static final CommandType COMMAND_TYPE = CommandType.TODO;
    private final String description;

    /**
     * Creates a TodoCommand that would add a Todo with the supplied description
     * to a TaskManager when executed.
     *
     * @param description Description of the Todo to be added.
     */
    private TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Constructs a TodoCommand from a commandMap.
     *
     * @param commandMap CommandMap representing the instruction.
     * @return TodoCommand object based on the commandMap.
     * @throws DukeException When the user inputs an illegal instruction for the
     * Todo command.
     */
    public static TodoCommand fromCommandMap(HashMap<String, List<String>> commandMap)
            throws DukeException {
        List<String> descriptionStrings = commandMap.get(COMMAND_STRING);
        if (descriptionStrings.isEmpty()) {
            throw new DukeException("Todo description cannot be empty");
        }

        String description = String.join(" ", descriptionStrings);
        return new TodoCommand(description);
    }

    /**
     * Adds the specified Todo Task to the supplied TaskManager.
     *
     * @param taskManager TaskManager object to add the Todo Task to.
     * @return String response of the action that was performed.
     */
    @Override
    public String execute(TaskManager taskManager) {
        return taskManager.addTask(new Todo(description));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoCommand that = (TodoCommand) o;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
