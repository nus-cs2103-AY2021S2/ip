package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

public class ByeCommand extends Command {
    public ByeCommand() {

    }
    public String execute(TaskList tasks, Storage storage) {
        return "Bye";
    }

}
