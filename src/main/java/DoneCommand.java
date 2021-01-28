public class DoneCommand extends Command {
    int taskNum;
    public DoneCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        Task t = tl.get(taskNum - 1);
        t = t.finishTask();
        tl.set(taskNum - 1, t);
        ui.printMarkedDone(t);
        storage.save(tl);
    }
}