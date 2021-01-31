package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand implements Command {
    private String fullCmd;
    private String[] fullCmdStrArray;
    private Ui ui;

    /**
     * Create and initialize a Find Command.
     *
     * @param fullCmd The full user input in String form.
     * @param ui The ui object responsible for displaying find messages to the CLI.
     */
    public FindCommand(String fullCmd, Ui ui) {
        this.fullCmd = fullCmd;
        this.fullCmdStrArray = fullCmd.split(" ", 2);
        this.ui = ui;
    }
    @Override
    public void run(Storage storage, TaskList taskList) throws DukeException {
        if (fullCmdStrArray.length == 1) { // handle find without parameters
            throw new DukeException("Sorry human, please tell me what to find.");
        }
        String toFind = fullCmdStrArray[1];
        TaskList matchingTasks = taskList.find(toFind);
        ui.printList(matchingTasks);
    }
}
