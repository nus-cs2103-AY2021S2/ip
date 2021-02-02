package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super(null);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int numberOfTasks = tasks.size();
        if (numberOfTasks == 0) {
            ui.showNewLine("You currently have no tasks added!");
        } else {
            for (int i = 0; i < numberOfTasks; i++) {
                String message = String.format("%d.%s", i, tasks.get(i));
                ui.showNewLine(message);
            }
        }
    }
}
