public class ListCommand implements Command{
    private String fullCmd;
    private String[] fullCmdStrArray;
    private Ui ui;

    public ListCommand(String fullCmd, Ui ui) {
        this.fullCmd = fullCmd;
        this.fullCmdStrArray = fullCmd.split(" ");;
        this.ui = ui;
    }

    @Override
    public void run() throws DukeException {
        if (fullCmdStrArray.length > 1) { // handle commands such as "list abc", "list 1 2 3"
            throw new DukeException(ui.listCmdError());
        }
        ui.printList(Duke.taskList);
    }
}
