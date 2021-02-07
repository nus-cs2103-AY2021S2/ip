package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) {
        String headerText;
        if (tasks.size() == 0) {
            headerText = "\tHmm... You do not have any tasks!\n";
        } else {
            headerText = "\tHere are the tasks in your list:\n";
        }
        return headerText + tasks.list();
    }

}
