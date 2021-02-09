package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super(CommandType.LIST);
    }

    public String execute(Ui ui, TaskList taskList, Storage storage) {
        return ui.getListResponse(taskList);
    }
}
