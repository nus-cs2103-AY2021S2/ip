package duke.command;

import duke.*;

public class ListCommand extends Command {

    public ListCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        ui.print("This is your to-do list:");
        for (int i = 0; i < taskList.size(); i++) {
            ui.print((i + 1) + ". " + taskList.get(i));
        }
    }
}
