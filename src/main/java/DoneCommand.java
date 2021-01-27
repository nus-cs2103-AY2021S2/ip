public class DoneCommand extends Command{

    private String command;

    public DoneCommand(String command){
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException, DukeWrongInputException {
        String[] commandArr = command.trim().split(" ");

        Task doneTask = taskList.getTaskAtIndex(Integer.parseInt(commandArr[1]) - 1);
        doneTask.markAsDone();
        ui.showTaskDone(doneTask);
    }

    /**
     * Indicates whether command is an exit command.
     * @return boolean value for whether command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}