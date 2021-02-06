public class ErrorCommand extends Command{

    public ErrorCommand(String info) {
        super(info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("OOPS!!! I'm sorry, but I don't know what that means.");
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
