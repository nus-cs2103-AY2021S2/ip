public class DoneCommand extends Command {
    String[] info;
    boolean isBye = false;

    public DoneCommand(String[] info) {
        this.info = info;
    }

    @Override
    public boolean isBye() {
        return info[0].equals("bye");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.done(info);
        ui.didTask(task);
    }
}
