import java.util.ArrayList;

/**
 * When the user requests for the current list of tasks,
 * the ListCommand is called
 */
public class ListCommand extends Command {
    private String command;

    public ListCommand(String command) {
        this.command = command;
    }

    @Override
    public String executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        return ui.showList(taskList);
    }

    public boolean isRunning() {
        return true;
    }
}
