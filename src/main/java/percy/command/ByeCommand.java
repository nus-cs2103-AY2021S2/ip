package percy.command;

import percy.task.TaskList;
import percy.ui.UserInterface;
import percy.storage.Storage;

public class ByeCommand extends Command {
    public static final String COMMAND = "bye";

    public ByeCommand() {
        super(true);
    }

    /**
     * Prints the bye message via the UI method, UI.bye().
     *
     * @param taskList The TaskList used to store the Tasks for this instance of Duke (not used in this method).
     */
    public String execute(TaskList taskList, Storage storage) {
        return UserInterface.makeByeMsg();
    }
}