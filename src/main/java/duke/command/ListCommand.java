package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ListCommand extends Command {

    public ListCommand() {
        super("list");
    }

    public String execute(TaskList tasks, Storage storage) {
        return tasks.toStringWithIndex();
    }

    public boolean isExit() {
        return false;
    }
}
