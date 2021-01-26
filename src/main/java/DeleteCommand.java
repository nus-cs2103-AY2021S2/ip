public class DeleteCommand extends Command {
    String[] info;

    public DeleteCommand(String[] info) {
        this.info = info;
    }

    @Override
    public boolean isBye() {
        return info[0].equals("bye");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.delete(info);
        ui.deletedTask(task, tasks.listLength);
    }
}
