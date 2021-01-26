public class FinishCommand extends Command{
    public FinishCommand(CommandEnum type) {
        super(type);
    }

    @Override
    public void execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        int[] finishList = snomio.readContentWithNumbers(type.name());
        Task[] finishedTasks = taskList.finishTask(finishList);
        snomio.showFinishedTasks(finishedTasks);
        storage.saveFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
