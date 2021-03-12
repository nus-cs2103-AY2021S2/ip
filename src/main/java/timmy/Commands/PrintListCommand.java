package timmy.Commands;

import timmy.Storage;
import timmy.TaskList;
import timmy.Ui;

/**
 * A command that represents printing the <code>TaskList</code>
 */
public class PrintListCommand extends Command {

    public PrintListCommand() {

    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        return ui.printList(tasklist);
    }
}
