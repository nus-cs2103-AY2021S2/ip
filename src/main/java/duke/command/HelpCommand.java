package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A HelpCommand is the command when the uer wants to terminate Duke
 */
public class HelpCommand extends Command {

    private static String helpGuide = "Here are the commands you can use!\n"
            + "bye : exits the program\n"
            + "list : displays the current list of tasks\n"
            + "done [num] : marks task [num] to done\n"
            + "delete [num] : deletes task [num] from the list\n"
            + "find [keyword] : searches for tasks containing [keyword]\n"
            + "todo [description] : adds a todo task with a description\n"
            + "deadline [description] /by [yyyy-mm-dd] : adds a deadline\n"
            + "event [description] /at [yyyy-mm-dd] : adds an event";

    /**
     * Initialises the reply to a message and notes that this is the HelpCommand
     */
    public HelpCommand() {
        super("");
        this.reply = helpGuide;
    }


    /**
     * Executes the HelpCommand
     * @param ui The ui to respond to the user's input
     * @param s The storage to save the TaskList to
     * @param list The current list of tasks
     * @return A reply to the HelpCommand
     */
    public String execute(Ui ui, Storage s, TaskList list) {
        return ui.reply(this.reply);
    }

}
