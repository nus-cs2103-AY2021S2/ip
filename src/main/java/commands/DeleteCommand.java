package commands;

import exceptions.InvalidArgumentException;
import tasklist.TaskList;

public class DeleteCommand extends CommandWithParameters {
    protected DeleteCommand(String commandBody) {
        super("delete", commandBody);
    }

    @Override
    public void run(TaskList taskList) {
        try {
            // this.commandOutputMsg =
            taskList.deleteTask(Integer.parseInt(this.commandBody));
        } catch (InvalidArgumentException e) {
            handleException(e);
        }

        assert this.commandOutputMsg != null;
    }
}
