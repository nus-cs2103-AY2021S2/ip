package commands;

import format.Ui;
import tasklist.TaskList;

public class ListCommand extends CommandWithNoParameters {
    public ListCommand(String commandBody) {
        super("list", commandBody);
    }

    @Override
    public void run(TaskList taskList) {
        if (!commandBody.isEmpty()) {
            handleTooManyArgs();
            return;
        }

        this.commandOutputMsg = Ui.stringifyTaskList(taskList);
        this.hasRunSuccessfully = true;

        assert !this.commandOutputMsg.isEmpty() : "empty command output in " + this.commandName;
    }
}
