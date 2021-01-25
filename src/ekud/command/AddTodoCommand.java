package ekud.command;

import ekud.common.exception.*;
import ekud.storage.*;
import ekud.task.*;
import ekud.ui.*;

public class AddTodoCommand extends AddCommand {
    public AddTodoCommand(String description) {
        super(description);
    }

    /**
     * Execute this command by adding a todo into the list, followed by common procedures of all add commands
     *
     * @param tasks   the list of tasks
     * @param ui      the user interface
     * @param storage the file writer
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new ToDo(description));
        super.execute(tasks, ui, storage);
    }
}
