package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    public FindCommand(String arguments) {
        super(arguments);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        String result = "Here's what I found:";
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTask().contains(getArguments())) {
                result = result + "\n" + (i + 1) + ". " + taskList.get(i);
            }
        }
        return result;
    }
}
