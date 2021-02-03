package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ByeCommand extends Command{
    private static String bye = "Goodbye, hope to not see you again.";
    public ByeCommand() {}
    public String execute(Storage storage, TaskList taskList, Ui ui, String command) {
        return bye;
    }
}
