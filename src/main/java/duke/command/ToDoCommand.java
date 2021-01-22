package duke.command;

import duke.Storage;
import duke.Ui;
import duke.TaskManager;
import duke.exception.DukeException;

public class ToDoCommand extends Command {
    private String name;

    public ToDoCommand(String name) {
        this.name = name;
    }

    public void execute(Ui ui, TaskManager tm, Storage st) throws DukeException {
        tm.addToDoTask(name);
        st.save(tm);

        ui.println("    added: " + name);
        ui.println(String.format("    Now you have %d task(s)",
                tm.getSize()));
    }
}
