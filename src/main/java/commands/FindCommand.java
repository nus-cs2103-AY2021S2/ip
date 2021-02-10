package commands;

import exceptions.MissingArgumentException;
import tasklist.TaskList;

public class FindCommand extends CommandWithParameters {
    // todo find command hasn't been re-implemented yet...

    public FindCommand(String commandBody) {
        super("find", commandBody);
    }

    @Override
    public void run(TaskList taskList) {
        try {
            handleNoArgs();
            this.commandOutputMsg = taskList.findTasks(this.commandBody);
        } catch (MissingArgumentException e) {
            handleException(e);
        }
    }
}
