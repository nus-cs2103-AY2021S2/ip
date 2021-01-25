public class DoneCommand implements Command {
    private String fullCmd;
    private String[] fullCmdStrArray;
    private Ui ui;

    public DoneCommand(String fullCmd, Ui ui) {
        this.fullCmd = fullCmd;
        this.fullCmdStrArray = fullCmd.split(" ");;
        this.ui = ui;
    }

    @Override
    public void run(Storage storage, TaskList taskList) throws DukeException {
        if (fullCmdStrArray.length > 2) { // too many parameters (>1)
            throw new DukeException(ui.doneCmdTooManyArgsError());
        }

        if (fullCmdStrArray.length < 2) { // no parameter
            throw new DukeException(ui.doneCmdNoArgsError());
        }

        if (!Parser.isNumber(fullCmdStrArray[1])) { // handle commands such as 'done a', 'done hello'
            throw new DukeException(ui.doneCmdInvalidArgsError());
        }

        if (fullCmd.length() > 5) {
            int taskIndex = Integer.parseInt(fullCmdStrArray[1]) - 1;
            if (taskIndex > taskList.getSize() - 1 || taskIndex < 0) {
                throw new DukeException("Sorry human, that task does not seem to exist.");
            }
            Task doneTask = taskList.getIndex(taskIndex);
            doneTask.markDone();
            storage.saveTaskList(taskList);
            ui.printDoneMessage(doneTask);
        }
    }

}
