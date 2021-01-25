public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}