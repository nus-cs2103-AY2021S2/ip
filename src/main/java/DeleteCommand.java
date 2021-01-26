public class DeleteCommand extends Command {
    protected String[] info;

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
        int listLength = tasks.getListLength();
        ui.deletedTask(task, listLength);
    }
}
