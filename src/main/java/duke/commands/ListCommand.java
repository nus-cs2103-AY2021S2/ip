package duke.commands;

import duke.tasks.TaskList;

public class ListCommand extends Command{
    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public void excecute(TaskList list) {
        ui.printList(list);
    }
}
