public class DoneCommand extends Command{
    private final int id;

    public DoneCommand(int id) {
        this.id = id;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (id < 1 || id > tasks.getSize()) {
            throw new DukeException("The task id is invalid.");
        }
        tasks.getTask(id).markAsDone();
        ui.showDone(id, tasks);
        storage.updateFile();
    }

    @Override
    boolean isExit() {
        return false;
    }
}
