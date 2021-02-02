package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a 'list' command.
 * Lists out all the tasks currently in the task list.
 */
public class ListCommand extends Command {

    public ListCommand(String arguments) {
        super(arguments);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        String result = "This is your to-do list:";
        for (int i = 0; i < taskList.size(); i++) {
            result = result + "\n" + ui.print((i + 1) + ". " + taskList.get(i));
        }
        return result;
    }
}
