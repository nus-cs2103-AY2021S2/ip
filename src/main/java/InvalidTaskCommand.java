public class InvalidTaskCommand extends Command {

    public InvalidTaskCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showInvalidTaskMessage();
    }
}
