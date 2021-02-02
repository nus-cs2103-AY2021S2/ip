public class DeleteCommand extends Command {
    private int taskno;

    DeleteCommand(int taskno) {
        this.taskno = taskno;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.get(this.taskno - 1).toString();
        tasks.remove(this.taskno - 1);
        ui.delete(task, tasks.size());
        storage.savetasks(tasks);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
