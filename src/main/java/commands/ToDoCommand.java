package commands;

import duke.Storage;
import duke.Ui;
import duke.TaskManager;
import exceptions.DukeException;

public class ToDoCommand extends Command {
    private String name;

    public ToDoCommand(String name) {
        this.name = name;
    }

    public void execute(Ui ui, TaskManager tm, Storage st) throws DukeException {
        tm.addToDoTask(name);
        st.save(tm);
    }
}
