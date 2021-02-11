package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Handles the List command of the user to list out all tasks.
 * Format of command: "list".
 */
public class ListCommand implements Command {
    protected ListCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() <= 0) {
            ui.printIndentOutput("The current list is empty.");
        } else {
            ui.printIndentOutput("Here are the tasks in you list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.printIndentOutput((i + 1) + ". " + tasks.getTask(i));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public static ListCommand buildInstance() {
        return new ListCommand();
    }
}
