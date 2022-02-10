package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String args;

    public FindCommand(String args) {
        this.args = args;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getFoundMessage(tasks.findTask(this.args));
    }
}
