public class ListCommand extends Command{
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
