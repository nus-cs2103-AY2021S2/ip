package ekud.command;

import ekud.storage.Storage;
import ekud.task.Task;
import ekud.task.TaskList;
import ekud.ui.Ui;

/**
 * Command that goes through the entire list and displays all the tasks with descriptions containing a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Construct a command that uses a keyword as filter and display the matching tasks.
     *
     * @param keyword The keyword to match against the descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints all tasks with descriptions that contain the specified keyword.
     *
     * @param tasks the list of tasks.
     * @param ui the user interface.
     * @param storage the file writer.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList toPrint = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                toPrint.add(task);
            }
        }

        ui.printLines(toPrint.isEmpty() ? "Nothing found, try another keyword?" : "Here's what I found!");
        for (Task task : toPrint) {
            ui.printLines("\t " + task.toString());
        }
    }
}
