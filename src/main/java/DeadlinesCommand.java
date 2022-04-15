/**
 * The type Deadlines command.
 */
public class DeadlinesCommand extends Command {
    private final String fullCommand;
    private final int DES_IDX = 9;
    private final int BY_IDX = 4;

    /**
     * Instantiates a new Deadlines command.
     *
     * @param fullCommand the full command
     */
    public DeadlinesCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeException.FileLoadError {
                String description =
                        fullCommand.substring(
                                DES_IDX, fullCommand.indexOf('/') - 1);
                String by = fullCommand.substring(
                        fullCommand.indexOf('/') + BY_IDX);
                Deadlines deadline = new Deadlines(description, by);
                taskList.addTask(deadline);
                ui.taskAddMsg(taskList);
                storage.save(taskList);
    }
    public Boolean isExit() {
        return false;
    }
}

