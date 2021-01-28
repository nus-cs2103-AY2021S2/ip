public class ListCommand extends Command {

    public ListCommand(String command, String task, String date) {
        super(command, task, date);
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
            ui.list(storage.read(),this.date);
    }

    @Override
    boolean isExit() {
        return false;
    }
    
}
