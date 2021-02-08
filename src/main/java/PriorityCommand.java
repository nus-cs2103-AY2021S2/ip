public class PriorityCommand extends Command {
    protected String[] info;

    public PriorityCommand(String[] info) {
        this.info = info;
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task prioritisedTask = tasks.addPriority(info);
        return ui.addedPriority(prioritisedTask);
    }
}
