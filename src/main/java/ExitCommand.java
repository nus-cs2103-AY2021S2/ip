public class ExitCommand extends Command{

    public ExitCommand(String command, String task, String date) {
        super(command, task, date);
        // TODO Auto-generated constructor stub
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        // TODO Auto-generated method stub
        ui.exitCommandString();
    }

    @Override
    boolean isExit() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
