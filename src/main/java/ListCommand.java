public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, TaskStorage storage) {
        ui.print(tasks);
    }
}
