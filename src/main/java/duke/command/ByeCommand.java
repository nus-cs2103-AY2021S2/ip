package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ByeCommand extends Command{
    private static String bye = "Goodbye, hope to not see you again.";

    public ByeCommand() {}

    /**
     * Process command bye given by user.
     * @param storage The storage to store result
     *        taskList TaskList associated to the current Duke
     *        ui The UI we use to print stuff
     *        command Sentences entered by the user
     */
    public String execute(Storage storage, TaskList taskList, Ui ui, String command) {
        return bye;
    }
}
