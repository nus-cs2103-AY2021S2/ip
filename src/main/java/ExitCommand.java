public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.writeToFile(taskList.getList());
        ui.showGoodbyeMessage();
        System.exit(0);
    }
}
