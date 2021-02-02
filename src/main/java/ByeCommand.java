public class ByeCommand extends Command {
    public ByeCommand() {
        super(null);
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.saveTasksToFile(tasks);
        ui.showNewLine("Bye bye!");
    }
}
