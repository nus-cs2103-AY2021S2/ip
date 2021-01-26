public class DeleteCommand extends Command{
    public DeleteCommand(CommandEnum type) {
        super(type);
    }

    @Override
    public void execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        int[] deleteList = snomio.readContentWithNumbers(type.name());
        Task[] deletedTasks = taskList.deleteTask(deleteList);
        snomio.showDeletedTasks(deletedTasks);
        storage.saveFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
