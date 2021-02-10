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
            // assert this.commandBody != null : "list command doesn't need arguments";
            return;
        }

        this.commandOutputMsg = Ui.stringifyTaskList(taskList);
        this.hasRunSuccessfully = true;

        assert !this.commandOutputMsg.isEmpty() : "empty command output in " + this.commandName;
    }
}
