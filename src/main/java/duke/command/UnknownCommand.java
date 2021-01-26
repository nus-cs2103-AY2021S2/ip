package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("       OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}