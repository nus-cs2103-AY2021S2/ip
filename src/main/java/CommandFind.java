public class CommandFind extends Command {
    private final String query;

    public CommandFind(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printCommand(this);
        tasks.findTasks(query);
    }

    @Override
    public String toDukeOutput() {
        return "Sure thing boss, I'll find them in a jiffy. ";
    }
}
