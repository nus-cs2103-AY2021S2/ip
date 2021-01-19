package commands;

import duke.Ui;
import duke.TaskManager;

public class EventCommand extends Command {
    private String name;
    private String flag;

    public EventCommand(String[] params) {
        this.name = params[0];
        this.flag = params[1];
    }

    public void execute(Ui ui, TaskManager tm) {
        tm.addEventTask(name, flag);
    }
}
