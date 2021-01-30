package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A ListCommand is when the user wants to view the current list of tasks
 */
public class ListCommand extends Command {

    /**
     * Initialises the reply, for the tasks to be added on to
     */
    public ListCommand() {
        super("");
    }

    /**
     * Executes the ListCommand
     * @param ui The ui to respond to the user's input
     * @param s The storage to save the TaskList to
     * @param list The current list of tasks
     * @return The reply to the ListCommand
     */
    public String execute(Ui ui, Storage s, TaskList list) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < list.getSize(); i++) {
            sb.append("\t");
            sb.append(i + 1).append(". ").append(list.getItem(i));
            if (i != list.getSize() - 1) {
                sb.append("\n");
            }
        }
        if (list.getSize() == 0) {
            sb.append("\tYour list is empty!");
        }
        this.reply = sb.toString();
        return ui.reply(this.reply);
    }
}

