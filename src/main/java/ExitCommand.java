/**
   * ExitCommand inherits Command
   * @param command the main action of the command
   * @param task is null for Exit Command
   * @param date is null for Exit Command
   */
public class ExitCommand extends Command {

    public ExitCommand(String command, String task, String date) {
        super(command, task, date);
    }

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.exitCommandString();
    }

    @Override
    boolean isExit() {
        return true;
    }
    
}
