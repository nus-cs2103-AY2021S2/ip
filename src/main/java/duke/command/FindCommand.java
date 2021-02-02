package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    public FindCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        ui.print("Here's what I found:");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTask().contains(getArguments())) {
                ui.print((i + 1) + ". " + taskList.get(i));
            }
        }
    }
}
