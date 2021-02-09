package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;
import vergil.types.exceptions.VergilException;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(CommandType.BYE);
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws VergilException {
        return ui.getFarewellMessage();
    }
}
