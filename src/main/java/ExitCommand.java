public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, TaskStorage storage) {
        Duke.toExit = true;
        ui.print("Goodbye. See you later!");
    }
}
