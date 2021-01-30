public class ExitCommand extends Command{
    public ExitCommand(){
        super.isExit = true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.write(taskList);
        ui.exit();
    }
}
