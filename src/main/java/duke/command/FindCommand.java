package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String kw;

    /**
     * Creates a FindCommand that will find tasks with matching keyword kw
     *
     * @param kw
     */
    public FindCommand(String kw) {
        super(false);
        this.kw = kw;
    }

    /**
     * Finds tasks with matching keyword kw
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.printIndented("Here are the matching tasks in your list:");
        int count = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).contains(kw)) {
                count++;
                ui.printIndented(String.format("%d.%s", count, taskList.get(i).toString()));
            }
        }
    }
}
