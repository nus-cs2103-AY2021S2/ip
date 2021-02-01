public class ExitCommand extends Command {

    public ExitCommand(String action, String info) {
        super(action, info);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
