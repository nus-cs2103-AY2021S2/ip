public class ExitCommand extends Command{

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbye();
    };

    @Override
    public boolean isExit() { 
        return true;
    }
    
}
