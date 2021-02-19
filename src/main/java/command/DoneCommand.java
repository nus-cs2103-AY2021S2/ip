package command;

import task.TaskManager;
import util.DukeException;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DoneCommand extends Command {
    public static final String COMMAND_STRING = "done";
    public static final CommandType COMMAND_TYPE = CommandType.DONE;
    private final int position;

    public DoneCommand(int position) {
        this.position = position;
    }

    public static DoneCommand fromCommandMap(HashMap<String, List<String>> commandMap)
            throws DukeException {
        try {
            List<String> descriptions = commandMap.get(COMMAND_STRING);
            String indexString = descriptions.get(0);
            int index = Integer.parseInt(indexString) - 1;
            return new DoneCommand(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide an index to mark as done.");
        } catch (NumberFormatException e) {
            throw new DukeException("Index number could not be understood");
        }
    }

    @Override
    public String execute(TaskManager taskManager) throws DukeException {
        return taskManager.markTaskDone(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoneCommand that = (DoneCommand) o;
        return position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
