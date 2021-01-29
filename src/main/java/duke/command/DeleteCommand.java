package duke.command;

import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.overwrite(taskList.delete(taskDescription, ui));
        } catch (Exception ex) {
            System.out.println("     " + ex.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
