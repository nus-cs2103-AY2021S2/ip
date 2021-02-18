package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.Locale;

/**
 * A FindCommand is when the user wants to find tasks containing a particular keyword
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor to initialise the keyword to find
     * @param keyword the keyword to find
     */
    public FindCommand(String keyword) {
        super("");
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand
     * @param ui The ui to respond to the user's input
     * @param s The storage to save the TaskList to
     * @param list The current list of tasks
     * @return The reply to the FindCommand
     */
    public String execute(Ui ui, Storage s, TaskList list) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < list.getSize(); i++) {
            if (list.getItem(i).getDescription().toLowerCase(Locale.ROOT).contains(keyword)) {
                sb.append("\t");
                sb.append(i + 1).append(". ").append(list.getItem(i));
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
