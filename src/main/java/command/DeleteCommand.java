package command;

import task.TaskManager;
import util.DukeException;
import util.Parser;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Command to remove the specified Task from a TaskManger.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_STRING = "delete";
    public static final CommandType COMMAND_TYPE = CommandType.DELETE;
    private final int position;

    /**
     * Creates a DeleteCommand that would delete the task at the specified
     * position in the TaskManager when executed.
     *
     * @param position The position / index of the task to be deleted.
     */
    public DeleteCommand(int position) {
        this.position = position;
    }

    /**
     * Constructs a DeleteCommand from a commandMap.
     *
     * @param commandMap CommandMap representing the instruction.
     * @return DeleteCommand object based on the commandMap.
     * @throws DukeException When the user inputs an argument that cannot be parsed
     * into a valid Integer position.
     */
    public static DeleteCommand fromCommandMap(HashMap<String, List<String>> commandMap)
            throws DukeException {

        assert Parser.extractCommandString(commandMap).equals(COMMAND_STRING)
                : COMMAND_STRING + "CommandFlag does not match";

        try {
            List<String> descriptions = commandMap.get(COMMAND_STRING);
            String indexString = descriptions.get(0);
            int index = Integer.parseInt(indexString) - 1;
            return new DeleteCommand(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide an index to delete from the list.");
        } catch (NumberFormatException e) {
            throw new DukeException("Index number could not be understood");
        }
    }

    /**
     * Deletes the pre-specified task in the supplied TaskManager.
     *
     * @param taskManager TaskManager object to delete a Task from.
     * @return String response of the deletion.
     * @throws DukeException If the specified position does not exist within the
     * supplied TaskManager.
     */
    @Override
    public String execute(TaskManager taskManager) throws DukeException {
        return taskManager.deleteTask(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteCommand that = (DeleteCommand) o;
        return position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
