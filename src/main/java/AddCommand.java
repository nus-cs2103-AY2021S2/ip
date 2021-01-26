public class AddCommand extends Command {
    String[] info;
    boolean isBye = false;

    public AddCommand(String[] info) {
        this.info = info;
    }

    @Override
    public boolean isBye() {
        return info[0].equals("bye");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(info);
        ui.addedTask(tasks);
    }
}
