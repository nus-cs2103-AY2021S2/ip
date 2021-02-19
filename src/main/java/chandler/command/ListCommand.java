package chandler.command;

import chandler.ChandlerException;
import chandler.Storage;
import chandler.TaskList;
import chandler.ui.Ui;

/**
 * The ListCommand class encapsulates information and methods about a ListCommand.
 */
public class ListCommand implements Command {
    private String[] fullCmdStrArray;
    private Ui ui;

    /**
     * Create and initialize a List Command.
     *
     * @param fullCmd The full user input in String form.
     * @param ui The ui object responsible for displaying list messages to the CLI.
     */
    public ListCommand(String fullCmd, Ui ui) {
        this.fullCmdStrArray = fullCmd.split(" ");;
        this.ui = ui;
    }

    /**
     *  Processes the list command by displaying the user's list of tasks on the CLI.
     *
     * @param storage The storage object that writes data to the saved data file of tasks.
     * @param taskList The list of tasks.
     * @throws ChandlerException if the format of the List command is invalid.
     */
    @Override
    public String run(Storage storage, TaskList taskList) throws ChandlerException {
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "Tasklist cannot be null";

        if (fullCmdStrArray.length > 1) { // handle commands such as "list abc", "list 1 2 3"
            throw new ChandlerException(ui.listCmdError());
        }
        return ui.returnList(taskList);
    }
}
