package todobeast.commands;

import todobeast.TaskList;
import todobeast.Ui;
import todobeast.exceptions.ToDoBeastException;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    public void execute(TaskList taskList, Ui ui) throws ToDoBeastException {
    }
}
