package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int index = Integer.parseInt(this.arguments);
        Task deletedTask = tasks.remove(index);
        ui.showNewLine("Noted I've removed this task:");
        ui.showNewLine(deletedTask.toString());
        ui.showNewLine(String.format("You now have %d tasks in the list", tasks.size()));
    }
}
