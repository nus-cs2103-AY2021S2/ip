package commands;

import exceptions.InvalidArgumentException;
import tasklist.TaskList;

public class DoneCommand extends CommandWithParameters {
    public DoneCommand(String commandBody) {
        super("done", commandBody);
    }

    @Override
    public void run(TaskList taskList) {
        try {
             this.commandOutputMsg = taskList.markDone(Integer.parseInt(this.commandBody));
        } catch (InvalidArgumentException e) {
            handleException(e);
        }

        assert !this.commandOutputMsg.isEmpty() : "empty command output in " + this.commandName;
    }
}
