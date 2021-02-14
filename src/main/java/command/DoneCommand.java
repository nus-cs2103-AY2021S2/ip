package command;

import task.TaskManager;
import util.DukeException;

public class DoneCommand implements Command {
    private final int position;
    private String message = "";

    public DoneCommand(int position) {
        this.position = position;
    }

    @Override
    public void execute(TaskManager taskManager) {
        taskManager.markTaskDone(position);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public CommandType commandType() {
        return CommandType.DONE;
    }
}
