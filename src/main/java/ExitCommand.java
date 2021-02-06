public class ExitCommand extends Command {

    public ExitCommand(String info) {
        super(info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
