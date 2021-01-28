public class ExitCommand extends Command{

    public ExitCommand(String command, String task, String date) {
        super(command, task, date);
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.exitCommandString();
    }

    @Override
    boolean isExit() {
        return true;
    }
    
}
