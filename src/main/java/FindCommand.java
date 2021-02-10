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
}
