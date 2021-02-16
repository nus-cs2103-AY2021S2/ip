package command;

import task.TaskManager;
import util.DukeException;

import java.util.HashMap;
import java.util.List;

public class DeleteCommand extends Command {
    public static final String COMMAND_STRING = "delete";
    public static final CommandType COMMAND_TYPE = CommandType.DELETE;
    private final int position;
    private String message = "";

    public DeleteCommand(int position) {
        this.position = position;
    }

    public static DeleteCommand fromCommandMap(HashMap<String, List<String>> commandMap)
            throws DukeException {
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

    @Override
    public void execute(TaskManager taskManager) throws DukeException {
        message = taskManager.deleteTask(position);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
