public class FindCommand extends Command {

    private String command;

    public FindCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException {
        ui.showFoundListItems();
        String[] commandArr = command.split(" ");
        for (int i = 1; i <= taskList.getTaskListLength(); i++) {
            if (taskList.getTaskAtIndex(i - 1).getDescription().contains(commandArr[1])) {
                String output = String.format("%s. %s", i, taskList.getTaskAtIndex(i - 1).toString());
                System.out.println(output);
            }
        }
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
