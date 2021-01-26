package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand implements Command {
    private String fullCmd;
    private String[] fullCmdStrArray;
    private Ui ui;

    public FindCommand(String fullCmd, Ui ui) {
        this.fullCmd = fullCmd;
        this.fullCmdStrArray = fullCmd.split(" ");;
        this.ui = ui;
    }
    @Override
    public void run(Storage storage, TaskList taskList) throws DukeException {
        if (fullCmdStrArray.length == 1) { // handle find without parameters
            throw new DukeException("Sorry human, please tell me what to find.");
        }
        String toFind = fullCmdStrArray[1];
        TaskList matchingTasks = taskList.find(toFind);
        if (matchingTasks.getSize() == 0) {
            throw new DukeException("Sorry human, no such task seems to exists.");
        } else {
            ui.printList(matchingTasks);
        }
    }
}
