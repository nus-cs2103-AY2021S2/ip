package commands;

import exceptions.InvalidArgumentException;
import tasklist.TaskList;

public class DoneCommand extends CommandWithParameters {
    protected DoneCommand(String commandBody) {
        super("done", commandBody);
    }

    @Override
    public void run(TaskList taskList) {
        try {
            // this.commandOutputMsg
            taskList.markDone(Integer.parseInt(this.commandBody));
        } catch (InvalidArgumentException e) {
            handleException(e);
        }

        assert this.commandOutputMsg != null;
    }
}
