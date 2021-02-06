package duke.command;

import duke.TaskList;
import duke.task.Task;
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
    public FindCommand(Ui ui,TaskList tasks) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Lists out all the tasks with the given keyword.
     */
    @Override
    public void execute(String parameters) {
        String string = "";
        string += "Here are the matching tasks in your list:\n";
        string += listToString(parameters.strip());
        ui.createDukeDialog(string);
    }

    private String listToString(String keyword) {
        String content = "";
        Integer count = 1;
        for (Task t: tasks.findTasksWithString(keyword)) {
            content += count.toString() + ".";
            content += t.toString();
            content += "\n";
            count++;
        }
        return content.trim();
    }
}
