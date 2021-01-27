/**
 * Command for showing the entire list of tasks.
 */
public class ListCommand extends Command{

    private String command;

    /**
     * Constructor method
     * @param command user command input
     */
    public ListCommand(String command){
        this.command = command;
    }

    /**
     * Execute method for list command.
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If list command is missing description.
     * @throws DukeWrongInputException If user input is not any of the inputs available.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException {
        ui.showListItems();
        for (int i = 1; i <= taskList.getTaskListLength(); i++) {
            String output = String.format("%s. %s", i, taskList.getTaskAtIndex(i - 1).toString());
            System.out.println(output);
        }
        ui.showNumberOfItems(taskList.getTaskListLength());
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