public class ExitCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        super.toggleExit();
    }
}
