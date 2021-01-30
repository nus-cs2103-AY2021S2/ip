package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    /**
     * Lists all tasks in taskList
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.printIndented("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            ui.printIndented(String.format("%d.%s", i + 1, taskList.get(i).toString()));
        }
    }
}
