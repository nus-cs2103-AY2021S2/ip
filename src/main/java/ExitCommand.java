import java.util.List;

public class ExitCommand extends Command {

    private String command;

    public ExitCommand(String command){
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException, DukeWrongInputException {
        return;
    }

    /**
     * Indicates whether command is an exit command.
     * @return boolean value for whether command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
