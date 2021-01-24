public class InvalidCommand extends Command {
    public void execute(TaskList tasks, Ui ui, TaskStorage storage) {
        ui.print("Invalid Command");
    }
}
