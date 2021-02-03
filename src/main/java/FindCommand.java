public class FindCommand extends Command {

    private String command;

    public FindCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException {
        return ui.showFoundListItems(taskList, command);
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
