/**
 * A command that represents printing the <code>TaskList</code>
 */
public class PrintListCommand extends Command {

    public PrintListCommand() {

    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.printList(tasklist);
    }
}
