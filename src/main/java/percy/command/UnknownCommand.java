package percy.command;

import percy.exception.UnknownCommandException;
import percy.task.TaskList;
import percy.ui.UserInterface;
import percy.storage.Storage;

/**
 * A Command meant to handle the event in which an unknown command is given by the user.
 */
public class UnknownCommand extends Command {

    public UnknownCommand() {
       super(false);
    }

    /**
     * Prints into the console the error message when an unknown command is given, via the instantiation of an
     * UnknownCommandException.
     *
     * @param taskList The TaskList from the main Duke object.
     */
    public String execute(TaskList taskList, Storage storage) {
        Exception ex = new UnknownCommandException();
        return UserInterface.makeMsg(ex.toString());
    }
}
