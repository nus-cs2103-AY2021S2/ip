import java.util.ArrayList;

/**
 * When the user bids goodbye, the Exit Command is called
 */
public class ExitCommand extends Command {
    private String command;

    public ExitCommand(String command) {
        this.command = command;
    }

    @Override
    public String executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        return ui.showGoodbye();
    }

    public boolean isRunning() {
        return false;
    }
}