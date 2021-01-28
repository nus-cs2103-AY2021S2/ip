public class DeleteCommand extends Command{
    private final int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (id < 1 || id > tasks.getSize()) {
            throw new DukeException("The task id is invalid.");
        }
        Task removed = tasks.removeTask(id);
        ui.showDeleted(removed, tasks);
        storage.updateFile();
    }

    @Override
    boolean isExit() {
        return false;
    }
}
