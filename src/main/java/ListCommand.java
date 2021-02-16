/**
   * ListCommand inherits Command
   * @param command the main action of the command (todo,deadline,event)
   * @param task is null for List Command
   * @param date is an optional input
   * If users specify date behind the list,
   * the output users would receive would be task that are due on tha specified date
   */
public class ListCommand extends Command {

    public ListCommand(String command, String task, String date) {
        super(command, task, date);
    }

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.list(storage.read(), this.date);
    }

    @Override
    boolean isExit() {
        return false;
    }
}