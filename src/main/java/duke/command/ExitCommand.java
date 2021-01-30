package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    /**
     * Exits programme
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.printIndented("Bye. Hope to see you again soon!");
    }
}
