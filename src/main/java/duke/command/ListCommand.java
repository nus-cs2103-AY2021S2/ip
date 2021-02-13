package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    @Override
    public CommandResult execute(Ui ui, TaskList tasks, Storage storage) {
        String listMessage = ui.getDisplayOfAllTasks(tasks);
        return new CommandResult(listMessage,false);
    }
}
