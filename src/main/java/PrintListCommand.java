public class PrintListCommand extends Command {
    public PrintListCommand() {

    }
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.printList(tasklist);
    }
}
