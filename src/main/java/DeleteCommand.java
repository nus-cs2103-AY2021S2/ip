public class DeleteCommand extends Command {
    int taskNum;
    public DeleteCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        Task t = tl.remove(taskNum - 1);
        ui.printDeletedTask(t);
        storage.save(tl);
    }
}