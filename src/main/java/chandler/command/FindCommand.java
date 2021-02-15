package chandler.command;

import chandler.ChandlerException;
import chandler.Storage;
import chandler.TaskList;
import chandler.ui.Ui;

public class FindCommand implements Command {
    private String[] fullCmdStrArray;
    private Ui ui;

    /**
     * Create and initialize a Find Command.
     *
     * @param fullCmd The full user input in String form.
     * @param ui The ui object responsible for displaying find messages to the CLI.
     */
    public FindCommand(String fullCmd, Ui ui) {
        this.fullCmdStrArray = fullCmd.split(" ", 2);
        this.ui = ui;
    }
    @Override
    public String run(Storage storage, TaskList taskList) throws ChandlerException {
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "Tasklist cannot be null";

        if (fullCmdStrArray.length == 1) { // handle find without parameters
            throw new ChandlerException(ui.findNoArgsError());
        }
        String toFind = fullCmdStrArray[1];
        TaskList matchingTasks = taskList.find(toFind);
        return ui.returnList(matchingTasks);
    }
}
