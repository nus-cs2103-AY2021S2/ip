public class DoneCommand extends Command{
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.updateFile();
    }

    @Override
    boolean isExit() {
        return false;
    }
}
