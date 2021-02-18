public class CommandList extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return this.toDukeOutput() + "\n" + tasks.printTasks();

    }

    @Override
    public String toDukeOutput() {
        return "Aye boss, here to see your \"collection\" eh?";
    }
}
