public class ListCommand extends Command {
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.printTasks(tl);
    }
}