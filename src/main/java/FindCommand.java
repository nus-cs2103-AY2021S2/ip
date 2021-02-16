  /**
   * FindCommand inherits Command
   * @param command the main action of the command
   * @param task the index of task user wants to mark as done
   * @param date is a null value for Done Command
   */
public class FindCommand extends Command{

    public FindCommand(String command, String task, String date) {
        super(command, task, date);
    }

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.findTaskString(this.task, storage.read());
    }

    @Override
    boolean isExit() {
        return false;
    }
    
}