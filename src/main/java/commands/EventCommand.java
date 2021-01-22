package commands;

import duke.Storage;
import duke.Ui;
import duke.TaskManager;
import exceptions.DukeException;

public class EventCommand extends Command {
    private String name;
    private String flag;

    public EventCommand(String[] params) {
        this.name = params[0];
        this.flag = params[1];
    }

    public void execute(Ui ui, TaskManager tm, Storage st) throws DukeException {
        tm.addEventTask(name, flag);
        st.save(tm);
    }
}
