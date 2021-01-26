public class ExitCommand extends Command{
    public ExitCommand(){
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.write(taskList);
        ui.exit();
    }
}
