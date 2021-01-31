package pason.commands;

import pason.storage.Storage;
import pason.tasks.TaskList;
import pason.ui.Ui;

public class ListCommand extends Command {
    /**
     * Initialises the AddCommand.
     */
    public ListCommand(String command) {
        super(command);
    }

    /**
     * Executes the command.
     */
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        if (tasks.getTasks().size() == 0) {
            ui.printMessage("There are no tasks in your list. Time to add some!");
        } else {
            String output = "Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                output += (i + 1) + ". " + tasks.getTasks().get(i) + "\n";
            }
            ui.printMessage(output);
        }
    }

    public boolean isExit() {
        return false;
    }
}
