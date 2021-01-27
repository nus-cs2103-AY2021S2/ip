package percy.command;

import percy.task.TaskList;
import percy.ui.UserInterface;
import percy.storage.Storage;

import java.io.IOException;

public class StartCommand extends Command {

    public StartCommand() {
        super(false);
    }

    /**
     * Prints the bye message via the UI method, UI.bye().
     *
     * @param taskList The TaskList used to store the Tasks for this instance of Duke (not used in this method).
     */
    public String execute(TaskList taskList, Storage storage) throws IOException {
        return UserInterface.makeStartUpMsg();
    }
}