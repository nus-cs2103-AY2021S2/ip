package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {

    public ExitCommand() {
        this.type = "bye";
        this.description = "";
        this.isExit = true;
    }

    /**
     * Terminates Duke.
     *
     * @param tasks Task List,
     * @param ui User interface.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
