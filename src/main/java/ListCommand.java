public class ListCommand extends Command{
    public ListCommand(CommandEnum type) {
        super(type);
    }

    @Override
    public void execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        snomio.showTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
