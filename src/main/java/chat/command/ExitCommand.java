package chat.command;

import chat.TaskList;
import chat.Storage;
import chat.Ui;

/**
 * A type of command that deals with user when exiting the programme.
 */
public class ExitCommand extends Command{

    /**
     * Method that prints exiting response from Chat the Cat to user.
     * @param tasks TaskList object that contains a list of tasks.
     * @param ui Ui object that gives responses to user.
     * @param storage Storage object that interacts with task data in hard disk.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbye();
    };

    /**
     * Returns true if command requires Chat the Cat to exit.
     * 
     * @return true.
     */
    @Override
    public boolean isExit() { 
        return true;
    }
    
}
