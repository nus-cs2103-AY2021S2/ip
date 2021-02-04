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
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        Task task = tasks.markTaskDone(indexToMarkDone);
        ui.setDisplayMessage("Nice! I've marked this task as done:\n" + "\t" + task);
    }
}
