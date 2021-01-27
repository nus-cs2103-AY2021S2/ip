public class DeleteCommand extends Command {

    private String command;

    public DeleteCommand(String command){
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException, DukeWrongInputException {
        String[] commandArr = command.trim().split(" ");
        ui.showTaskDeleted(taskList.getTaskAtIndex(Integer.parseInt(commandArr[1]) - 1));
        taskList.delete(Integer.parseInt(commandArr[1]) - 1);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}