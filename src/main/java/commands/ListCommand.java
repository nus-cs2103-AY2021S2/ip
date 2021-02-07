package commands;

import format.Ui;
import tasklist.TaskList;

public class ListCommand extends CommandWithNoParameters {
    public ListCommand(String commandBody) {
        super("list", commandBody);
    }

    @Override
    public void run(TaskList taskList) {
        if (commandBody == null) {
            this.commandOutputMsg = Ui.stringifyTaskList(taskList);
        } else {
            handleTooManyArgs();
        }
        assert this.commandOutputMsg != null;
    }
}
