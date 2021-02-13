package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class MarkTaskCommand extends Command {

    private int indexToMarkDone;

    public MarkTaskCommand(int indexToMarkDone) {
        this.indexToMarkDone = indexToMarkDone;
    }

    @Override
    public CommandResult execute(Ui ui, TaskList tasks, Storage storage) {
        Task task = tasks.markTaskDone(indexToMarkDone);
        String MarkTaskMessage = ui.generateMarkTaskMessage(task);
        return new CommandResult(MarkTaskMessage,false);
    }
}
