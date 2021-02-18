package duke.command;

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
    public ByeCommand() {
        super("Bye. Hope to see you again soon!");
        this.isBye = true;
    }


    /**
     * Executes the ByeCommand
     * @param ui The ui to respond to the user's input
     * @param s The storage to save the TaskList to
     * @param list The current list of tasks
     * @return A reply to the ByeCommand
     */
    public String execute(Ui ui, Storage s, TaskList list) {
        return ui.reply(this.reply);
    }

}
