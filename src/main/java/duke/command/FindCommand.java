package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;

/**
 * Command to find indicated keyword in all the tasks' descriptions.
 */
public class FindCommand implements ICommand {
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor method for FindCommand
     * @param ui
     * @param tasks
     */
    public FindCommand(Ui ui, TaskList tasks) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Lists out all the tasks with the given keyword.
     */
    @Override
    public void execute(String keyword) {
        String string = "";
        string += "Here are the matching tasks in your list:\n";
        string += listToString(keyword.strip());
        ui.createDukeDialog(string);
    }

    private String listToString(String keyword) {
        return Parser.listTaskToString(this.tasks.findTasksWithString(keyword));
    }
}
