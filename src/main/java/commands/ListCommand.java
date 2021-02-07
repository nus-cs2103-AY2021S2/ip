package commands;

import format.Ui;
import tasklist.TaskList;

public class ListCommand extends Command {
    public ListCommand(String commandBody) {
        super("list", commandBody);
    }

    @Override
    public void run(TaskList taskList) {
        this.commandOutput = Ui.stringifyTaskList(taskList);
        // assert commandOutput is non empty i guess
    }
}
