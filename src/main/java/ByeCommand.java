public class ByeCommand extends Command{

    ByeCommand(String[] parsedAction) {
        super(parsedAction);
    }

    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        ui.showByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
