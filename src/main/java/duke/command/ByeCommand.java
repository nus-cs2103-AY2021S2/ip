package duke.command;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A ByeCommand is the command when the uer wants to terminate Duke
 */
public class ByeCommand extends Command {
    /**
     * Initialises the reply to a message and notes that this is the ByeCommand
     */
    public ByeCommand(){
        super("Bye. Hope to see you again soon!");
        this.isBye = true;
    }

    /**
     * Executes the ByeCommand
     * @param ui the ui to respond to the user's input
     * @param s The storage to save the tasklist to
     * @param list The current list of tasks
     */
    public void execute(Ui ui, Storage s, TaskList list){
        ui.reply(this.reply);
    }

}
