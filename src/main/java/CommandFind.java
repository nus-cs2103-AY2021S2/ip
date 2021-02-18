public class CommandFind extends Command {
    private final String query;

    public CommandFind(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return this.toDukeOutput() + "\n" + tasks.findTasks(query);
    }

    @Override
    public String toDukeOutput() {
        return "Sure thing boss, I'll find them in a jiffy.";
    }
}
