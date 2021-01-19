package commands;

import duke.Ui;
import duke.TaskManager;

public class DeadlineCommand extends Command {
    private String name;
    private String flag;

    public DeadlineCommand(String[] params) {
        this.name = params[0];
        this.flag = params[1];
    }

    public void execute(Ui ui, TaskManager tm) {
        tm.addDeadlineTask(name, flag);
    }
}
