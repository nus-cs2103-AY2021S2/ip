package duke.command;

import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;

public class ListCommand extends Command {
    public ListCommand(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listCommandInteraction(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
