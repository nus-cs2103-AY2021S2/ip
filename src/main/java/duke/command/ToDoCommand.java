package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.exception.DukeException;

public class ToDoCommand extends Command {
    private String name;

    /**
     *  ToDoCommand constructor.
     *
     *  @param name Name of ToDo Task.
     */
    public ToDoCommand(String name) {
        assert name.length() > 0 : "Empty Name";
        this.name = name;
    }

    /**
     *  Executes ToDoCommand.
     *
     *  @param tm TaskManager Object from Duke.
     *  @param st Storage Object from Duke.
     *  @return Command response.
     *  @throws DukeException If any error arises from execution.
     */
    public String execute(TaskManager tm, Storage st) throws DukeException {
        tm.addToDoTask(name);
        st.save(tm);
        String res = String.format("added: %s\n Now you have %d task(s)",
                name,
                tm.getSize());
        return res;
    }
}
